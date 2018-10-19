/**
 * Created by songwei on 24/03/2017.
 */
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class PostOffice extends Thread
{

    private static int customer = 50;
    private static int postalWorker = 3;
    Semaphore max_capacity = new Semaphore(10);
    Semaphore postal_worker = new Semaphore(3);
    Semaphore cust_ready = new Semaphore(0);
    Semaphore[] finished = new Semaphore[50];
    Semaphore scale = new Semaphore(1);
    Semaphore mutex = new Semaphore(1);
    Queue <Integer> custQueue = new ArrayDeque<>();

    public void creat()
    {
        int i ;
        //Create thread customer and postal worker
        Thread[] cust = new Thread[customer];
        Thread[] worker = new Thread[postalWorker];
        System.out.println("Creating 50 customers and 3 postal workers in a Post Office:\n");

        for (i = 0; i < customer; i++)  //Create finish semaphore
        {
            finished[i] = new Semaphore(0);
        }

        for (i = 0; i < customer; i++) //Create customers
        {
            int order = (int) (Math.random() * 3);  // randomly assign a task
            cust[i] = new Customer(i, order);
            System.out.println("Customer " + i + " created");
            cust[i].start();
        }

        for (i = 0; i < postalWorker; i++) //Create postal workers
        {
            worker[i] = new PostalWorker(i);
            System.out.println("Postal Worker " + i + " created");
            worker[i].start();
        }

        for (i = 0; i < customer; i++)
        {
            try
            {
                cust[i].join();  //End customer thread
                System.out.println("Joined customer " + i);
            } catch (InterruptedException ex)
            {

            }
        }

        System.exit(0);
    }

    class Customer extends Thread  //Build Customer class
    {

        private int cust_number;
        private int cust_order;

        private Customer(int i, int order)  //Build Customer constructor
        {
            cust_number = i;
            cust_order = order;
        }

        public void run()
        {
            try
            {
                max_capacity.acquire();  // wait(max_capacity) only 10 customers can stay in the post office
                System.out.println("Customer " + cust_number + " enters post office"); // enter post office
                postal_worker.acquire(); // wait(worker), wait for worker to be ready
                mutex.acquire();  //wait(mutex): queue customer number and task number
                custQueue.add(cust_number);
                custQueue.add(cust_order);
                cust_ready.release(); //signal(customer_ready): tell worker that customer is ready
                mutex.release(); //signal(mutex):tell worker the customer and task number
                finished[cust_number].acquire(); // wait(finish[cust_number]) for worker to finish task
                System.out.println("Customer " + cust_number + taskFinish(cust_order));
                System.out.println("Customer " + cust_number + " leaves post office");
                max_capacity.release(); //signal(max_capacity):allow the next customer to come into post office
            } catch (InterruptedException ex)
            {
                System.out.println(ex.getMessage());
            }
        }


        private String taskFinish(int i)
        {
            switch (i) {
                case 0:
                    return " finished buying stamps";
                case 1:
                    return " finished mailing a letter";
                case 2:
                    return " finished mailing a package";
                default:
                    return "";
            }
        }

    }

    class PostalWorker extends Thread
    {

        private int worker_number;
        private int cust;

        private PostalWorker(int i)
        {
            worker_number = i;
        }

        public void run()
        {
            int cust_num;
            int cust_order;
            while (true)
            {
                try
                {
                    cust_ready.acquire(); //wait(customer_ready):wait for customer to get ready
                    mutex.acquire(); //wait(mutex):send customer and task number to worker
                    cust_num = custQueue.poll(); //dequeue customer number
                    cust_order = custQueue.poll(); //dequeue task number
                    System.out.println("Customer " + cust_num + " asks postal worker " + worker_number + taskStatement(cust_order));
                    System.out.println("Postal Worker " + worker_number + " serving customer " + cust_num + "");
                    cust = cust_num;
                    mutex.release();
                    serve(cust_order); // postal worker starts to do task
                    System.out.println("Postal Worker " + worker_number + " finished serving customer " + cust);
                    finished[cust].release(); //signal(finish(customer):tell costumer the task has been finished
                    postal_worker.release(); // signal(worker):tell costumer worker is available
                } catch (InterruptedException e) {

                }
            }
        }
        private String taskStatement(int i)
        {
            if(i == 0)
            {
                return " buy stamps";
            }

            else if(i == 1)
            {
                return " mail a letter";
            }

            else if (i ==2)
            {
                return " mail a package";
            }

            else return "";

        }

        public void serve(int task) throws InterruptedException
        {
            switch(task)
            {
                case 1: //Buy stamps 60s
                    Customer.sleep(1000);
                    break;
                case 2: //Mail a letter 90s
                    Customer.sleep(1500);
                    break;
                case 3: //Mail a package 120s
                    scale.acquire(); //only one scale in post office
                    System.out.println("Scale is using by postal worker " + worker_number);
                    Customer.sleep(2000);
                    System.out.println("Postal worker " + worker_number + " finished using scale");
                    scale.release(); //tell other worker the scale is available now
                    break;
                default: break;
            }
        }

    }

    public static void main(String[] args)
    {
        PostOffice postOffice = new PostOffice();
        postOffice.creat();
    }
}
