import requests

# URL của trang web hoặc ứng dụng bạn muốn gửi yêu cầu POST đến
url = "https://script.google.com/macros/s/AKfycbyW-nmxwX7Cch739uccKFTGpJQXzB-9R0jBzwoKO5lkBU5BkWSgR1t17oafUwb-pTSm/exec" 

data = {
    "source": "vi",
    "target": "en",
    "text": "tôi rất thích cô ấy "
}

response = requests.post(url,data= data)

print(response.text)