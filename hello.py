#from PIL import Image
#from skimage.filters import threshold_local
import cv2
import numpy as np
import pytesseract
import utlis
#import time

pytesseract.pytesseract.tesseract_cmd = r'E:\\TesseractOCR\\tesseract.exe'

pathImage="TestSet122.jpg"
image = cv2.imread(pathImage, cv2.IMREAD_ANYCOLOR)
heightImg,widthImg,_= image.shape
heightImg = 800
widthImg= 800

img = cv2.imread(pathImage)
img = cv2.resize(img, (widthImg, heightImg)) # RESIZE IMAGE
imgBlank = np.zeros((heightImg,widthImg, 3), np.uint8) # 테스트 디버깅 위한 블랭크 이미지
imgGray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY) # 그레이
imgBlur = cv2.GaussianBlur(imgGray, (5, 5), 1) # 가우시안 블러
thres = utlis.valTrackbars()  # GET TRACK BAR VALUES FOR THRESHOLDS
imgThreshold = cv2.Canny(imgBlur, thres[0], thres[1])  # APPLY CANNY BLUR
kernel = np.ones((3, 3))


imgDial = cv2.dilate(imgThreshold, kernel, iterations=2)  # APPLY DILATION
imgThreshold = cv2.erode(imgDial, kernel, iterations=1)  # APPLY EROSION
   

## FIND ALL COUNTOURS
imgContours = img.copy()  # COPY IMAGE FOR DISPLAY PURPOSES
imgBigContour = img.copy()  # COPY IMAGE FOR DISPLAY PURPOSES
contours, hierarchy = cv2.findContours(imgThreshold, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)  # FIND ALL CONTOURS
cv2.drawContours(imgContours, contours, -1, (0, 255, 0), 10)  # DRAW ALL DETECTED CONTOURS

# FIND THE BIGGEST COUNTOUR
biggest, maxArea = utlis.biggestContour(contours)  # FIND THE BIGGEST CONTOUR
if biggest.size != 0:
    biggest = utlis.reorder(biggest)
    cv2.drawContours(imgBigContour, biggest, -1, (0, 255, 0), 20)  # DRAW THE BIGGEST CONTOUR
    imgBigContour = utlis.drawRectangle(imgBigContour, biggest, 2)
    pts1 = np.float32(biggest)  # PREPARE POINTS FOR WARP
    pts2 = np.float32([[0, 0], [widthImg, 0], [0, heightImg], [widthImg, heightImg]])  # PREPARE POINTS FOR WARP
    matrix = cv2.getPerspectiveTransform(pts1, pts2)
    imgWarpColored = cv2.warpPerspective(img, matrix, (widthImg, heightImg))

    # REMOVE 20 PIXELS FORM EACH SIDE
    imgWarpColored = imgWarpColored[20:imgWarpColored.shape[0] - 20, 20:imgWarpColored.shape[1] - 20]
    imgWarpColored = cv2.resize(imgWarpColored, (widthImg, heightImg))

    # APPLY ADAPTIVE THRESHOLD
    imgWarpGray = cv2.cvtColor(imgWarpColored, cv2.COLOR_BGR2GRAY)
    imgAdaptiveThre = cv2.adaptiveThreshold(imgWarpGray, 255, 1, 1, 7, 2)
    imgAdaptiveThre = cv2.bitwise_not(imgAdaptiveThre)
    imgAdaptiveThre = cv2.medianBlur(imgAdaptiveThre, 3)
else:
    pass

print(pytesseract.image_to_string(imgAdaptiveThre,lang='kor'))

cv2.imshow("Result",imgAdaptiveThre)
cv2.waitKey(0)



"""
def mouse_callback(event,x,y,flags,param):
    global point_list,count,img_original

    if event == cv2.EVENT_LBUTTONDOWN:
        print("(%d,%d)"%(x,y))
        point_list.append((x,y))

        print(point_list)
        cv2.circle(img_original,(x,y),3,(0,0,255),-1)


cv2.namedWindow('original')
cv2.setMouseCallback('original',mouse_callback)

image = cv2.imread('test12.png')

while(True):
    cv2.imshow("original",image)
    height,width = image.shape[:2]
    if cv2.waitKey(1)&0xFF==32:
        break




image = cv2.imread('test12.png')

#image=cv2.resize(image,dsize=(500,500),interpolation=cv2.INTER_AREA) # 리사이즈
print("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ")

print(pytesseract.image_to_string(image,lang='kor'))

print("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ")



#글자 박스 그리기

hImag,wImag,_=image.shape
boxes = pytesseract.image_to_boxes(image,lang='kor')

for b in boxes.splitlines():
    b = b.split(' ')
    #print(b)
    x,y,w,h=int(b[1]),int(b[2]),int(b[3]),int(b[4])
    cv2.rectangle(image,(x,hImag-y),(w,hImag-h),(0,0,255),1)

cv2.imshow('Result',image)
cv2.waitKey(0)
"""
"""
print('ㅡㅡㅡㅡㅡㅡㅡㅡㅡ전처리ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ')
image = cv2.imread('test6.jpg')
hImag,wImag,_=image.shape
if hImag >2000:
    hImag = int(hImag/4)
if wImag >2000:
    wImag = int(wImag/4)

#gray = cv2.cvtColor(image,cv2.COLOR_BGR2GRAY)
image=cv2.resize(image,dsize=(hImag,wImag),interpolation=cv2.INTER_AREA)


boxes = pytesseract.image_to_boxes(image,lang='kor')
print(pytesseract.image_to_string(image, lang='kor'))

for b in boxes.splitlines():
    b = b.split(' ')
    print(b)
    x,y,w,h=int(b[1]),int(b[2]),int(b[3]),int(b[4])
    cv2.rectangle(image,(x,hImag-y),(w,hImag-h),(0,0,255),1)

cv2.imshow('Result',image)
cv2.waitKey(0)

"""
