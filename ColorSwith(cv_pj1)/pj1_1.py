import cv2
import numpy as np
import sys

if(len(sys.argv) != 7) :
    print(sys.argv[0], ": takes 6 arguments. Not ", len(sys.argv)-1)
    print("Expecting arguments: w1 h1 w2 h2 ImageIn ImageOut.")
    print("Example:", sys.argv[0], " 0.4 0.1 0.6 0.5 fruits.jpg out.png")
    sys.exit()

w1 = float(sys.argv[1])
h1 = float(sys.argv[2])
w2 = float(sys.argv[3])
h2 = float(sys.argv[4])
name_input = sys.argv[5]
name_output = sys.argv[6]

if(w1<0 or h1<0 or w2<=w1 or h2<=h1 or w2>1 or h2>1) :
    print(" arguments must satisfy 0 <= w1 < w2 <= 1, 0 <= h1 < h2 <= 1")
    sys.exit()

inputImage = cv2.imread(name_input, cv2.IMREAD_COLOR)
if(inputImage is None) :
    print(sys.argv[0], ": Failed to read image from: ", name_input)
    sys.exit()

cv2.imshow("input image: " + name_input, inputImage)
print(inputImage.shape)

rows, cols, bands = inputImage.shape # bands == 3
W1 = round(w1*(cols-1))
H1 = round(h1*(rows-1))
W2 = round(w2*(cols-1))
H2 = round(h2*(rows-1))

print(W1,H1,W2,H2)

# The transformation should be based on the
# historgram of the pixels in the W1,W2,H1,H2 range.
# The following code goes over these pixels


tmp = np.copy(inputImage)
#compute max and min L in selected window
mtxL_window = []
for i in range(H1, H2):
    for j in range(W1, W2):
        b, g, r = inputImage[i, j]

        gray = round(0.3 * r + 0.6 * g + 0.1 * b + 0.5)
        tmp[i, j] = [gray, gray, gray]


        b = b / 255
        if b < 0:
            b = 0
        elif b > 1:
            b = ((1 + 0.055) / 1.055) ** 2.4
        elif b >= 0 and b < 0.03928:
            b = b / 12.92
        else:
            b = ((b + 0.055) / 1.055) ** 2.4

        g = g / 255
        if g < 0:
            g = 0
        elif g > 1:
            g = ((1 + 0.055) / 1.055) ** 2.4
        elif g >= 0 and g < 0.03928:
            g = g / 12.92
        else:
            g = ((g + 0.055) / 1.055) ** 2.4

        r = r / 255
        if r < 0:
            r = 0
        elif r > 1:
            r = ((1 + 0.055) / 1.055) ** 2.4
        elif r >= 0 and r < 0.03928:
            r = r / 12.92
        else:
            r = ((r + 0.055) / 1.055) ** 2.4
        mtx = np.array([[0.412453, 0.35758, 0.180423], [0.212671, 0.71516, 0.072169], [0.019334, 0.119193, 0.950227]])
        RGB = np.array([[r], [g], [b]])
        XYZ = np.matmul(mtx, RGB)

        t = XYZ[1,0]/1.0
        if t > 0.008856:
            L_window = 116 * (t ** (1/3))-16
        else:
            L_window = 903.3 * t
        mtxL_window.append(L_window)
L_window_max = max(mtxL_window)
L_window_min = min(mtxL_window)

print("maxL_window=", L_window_max,"; minL_window=", L_window_min)
cv2.imshow('tmp', tmp)

# end of example of going over window

outputImage = np.zeros([rows, cols, bands], dtype=np.uint8)
#outputImage = np.copy(inputImage)

#stretch L based on Lmax and Lmin

for i in range(0, rows):
    for j in range(0, cols):
        b, g, r = inputImage[i, j]

        #bgr->Luv

        b = b / 255
        if b < 0:
            b = 0
        elif b > 1:
            b = ((1 + 0.055) / 1.055) ** 2.4
        elif b >= 0 and b < 0.03928:
            b = b / 12.92
        else:
            b = ((b + 0.055) / 1.055) ** 2.4

        g = g / 255
        if g < 0:
            g = 0
        elif g > 1:
            g = ((1 + 0.055) / 1.055) ** 2.4
        elif g >= 0 and g < 0.03928:
            g = g / 12.92
        else:
            g = ((g + 0.055) / 1.055) ** 2.4

        r = r / 255
        if r < 0:
            r = 0
        elif r > 1:
            r = ((1 + 0.055) / 1.055) ** 2.4
        elif r >= 0 and r < 0.03928:
            r = r / 12.92
        else:
            r = ((r + 0.055) / 1.055) ** 2.4

        mtx = np.array([[0.412453, 0.35758, 0.180423], [0.212671, 0.71516, 0.072169], [0.019334, 0.119193, 0.950227]])
        RGB = np.array([[r], [g], [b]])
        XYZ = np.matmul(mtx, RGB)

        t = XYZ[1, 0] / 1.0
        if t != 0:
            if t > 0.008856:
                L = 116 * (t ** (1 / 3)) - 16
            else:
                L = 903.3 * t

            d = XYZ[0, 0] + 15 * XYZ[1, 0]+ 3 * XYZ[2, 0]
            u = 13 * L * ((4 * XYZ[0, 0]/d) - 0.1977)
            v = 13 * L * ((9 * XYZ[1, 0]/d) - 0.4683)
        else:
            L = 0
            u = 0
            v = 0


        if L < L_window_min:
            L = 0
        elif L > L_window_max:
            L = 100
        else:
            L = (100 - 0) * (L - L_window_min)/(L_window_max - L_window_min) + 0
        print(L,u,v)

        #Luv->XYZ
        if L == 0:
            X = 0
            Y = 0
            Z = 0
        else:
            u_prime = (u + 13 * 0.1977 * L)/(13 * L)
            v_prime = (v + 13 * 0.4683 * L)/(13 * L)
            if L > 7.9996:
                Y = (((L + 16)/116) ** 3) * 1.0
            else:
                Y = (L/903.3) * 1.0

            if v_prime == 0:
                X = 0
                Z = 0
            else:
                X = Y * 2.25 * u_prime/v_prime
                Z = Y * (3 - 0.75 * u_prime - 5 * v_prime)/v_prime

        XYZ_reverse = np.array([[X], [Y], [Z]])
        mtx_reverse = np.array([[3.240479, -1.53715, -0.498535], [-0.969256, 1.875991, 0.041556], [0.055648, -0.204043, 1.057311]])
        RGB_reverse = np.matmul(mtx_reverse, XYZ_reverse)

        r_reverse = RGB_reverse[0, 0]
        g_reverse = RGB_reverse[1, 0]
        b_reverse = RGB_reverse[2, 0]

        if r_reverse < 0.00304:
            r_reverse = 12.92 * r_reverse
        else:
            r_reverse = 1.055 * (r_reverse ** (1/2.4)) - 0.055


        if g_reverse < 0.00304:
            g_reverse = 12.92 * g_reverse
        else:
            g_reverse = 1.055 * (g_reverse ** (1/2.4)) - 0.055

        if b_reverse < 0.00304:
            b_reverse = 12.92 * b_reverse
        else:
            b_reverse = 1.055 * (b_reverse ** (1 / 2.4)) - 0.055

        if r_reverse < 0:
            r_reverse = 0
        elif r_reverse > 1:
            r_reverse =1

        if g_reverse < 0:
            g_reverse = 0
        elif g_reverse > 1:
            g_reverse =1

        if b_reverse < 0:
            b_reverse = 0
        elif b_reverse > 1:
            b_reverse =1


        r_reverse = r_reverse * 255
        g_reverse = g_reverse * 255
        b_reverse = b_reverse * 255
        outputImage[i,j] = [b_reverse, g_reverse, r_reverse]

cv2.imshow("output:", outputImage)
cv2.imwrite(name_output, outputImage)


# wait for key to exit
cv2.waitKey(0)
cv2.destroyAllWindows()
