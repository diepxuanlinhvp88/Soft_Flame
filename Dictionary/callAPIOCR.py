import requests
import sys
def imageToText(path):
	api_url = 'https://api.api-ninjas.com/v1/imagetotext'
	image_file_descriptor = open(path, 'rb')
	files = {'image': image_file_descriptor}
	header = {'X-Api-Key': 'tMLnXM+X9y8sDHxxFgZ51Q==AvB6keikrBbShmFg'}
	r = requests.post(api_url,headers = header, files=files).json()
	tmp = ""
	for i in r:
		tmp+=i["text"]
	return tmp;

if __name__ == "__main__":
	print(imageToText(sys.argv[1]))