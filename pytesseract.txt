from PIL import Image
import pytesseract

pytesseract.pytesseract.tesseract_cmd=r"C:\Program Files\Tesseract-OCR\tesseract.exe"

image=Image.open('result8.jpg')

image_to_text=pytesseract.image_to_string(image,lang='kor')

print(image_to_text)