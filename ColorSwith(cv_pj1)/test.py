import cv2
import numpy as np
import sys


# The transformation should be based on the
# historgram of the pixels in the W1,W2,H1,H2 range.
# The following code goes over these pixels


HW3 =np.array([[[0,0,0],[0,0,0],[0,0,0],[0,0,0]],[[255,0,0],[255,0,0],[255,0,0],[255,0,0]],[[100,100,100],[100,100,100],[100,100,100],[100,100,100]],[[0,100,100],[0,100,100],[0,100,100],[0,100,100]]])
XYZ = np.zeros([4, 4, 3], dtype=np.uint8)

for i in range(0, 4) :
    for j in range(0, 4) :
        r, g, b = HW3[i, j]

        b = b / 255
        if b < 0.03928:
            b = b / 12.92
        else:
            b = ((b + 0.055) / 1.055) ** 2.4

        g = g / 255
        if g < 0.03928:
            g = g / 12.92
        else:
            g = ((g + 0.055) / 1.055) ** 2.4

        r = r / 255
        if r < 0.03928:
            r = r / 12.92
        else:
            r = ((r + 0.055) / 1.055) ** 2.4

        print(r,g,b)
        mtx = np.array([[0.412453, 0.35758, 0.180423], [0.212671, 0.71516, 0.072169], [0.019334, 0.119193, 0.950227]])
        RGB = np.array([[r], [g], [b]])
        XYZ = np.matmul(mtx, RGB)
        print(XYZ)

        #print("y = ", XYZ[2,0])

        t = XYZ[1, 0] / 1.0
        if t != 0:
            if t > 0.008856:
                L = 116 * (t ** (1 / 3)) - 16
            else:
                L = 903.3 * t

            d = XYZ[0, 0] + 15 * XYZ[1, 0] + 3 * XYZ[2, 0]
            u = 13 * L * ((4 * XYZ[0, 0] / d) - 0.1977)
            v = 13 * L * ((9 * XYZ[1, 0] / d) - 0.4683)
        else:
            L = 0
            u = 0
            v = 0
        print(L,u,v)










