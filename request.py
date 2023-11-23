# import requests

# # URL của trang web hoặc ứng dụng bạn muốn gửi yêu cầu POST đến
# url = "https://script.google.com/macros/s/AKfycbyW-nmxwX7Cch739uccKFTGpJQXzB-9R0jBzwoKO5lkBU5BkWSgR1t17oafUwb-pTSm/exec" 

# data = {
#     "source": "vi",
#     "target": "en",
#     "text": "tôi rất thích cô ấy "
# }

# response = requests.post(url,data= data)

# print(response.text)

import requests

key = "4b5b53420a2d413eb122e9c3c44bcb18"
url = "https://api.voicerss.org/?key="+key+"&hl=en-us&v=Linda&src=hello"

res= requests.post(url)
print(res.content)



