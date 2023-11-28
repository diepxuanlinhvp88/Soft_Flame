import requests
import json
import sys
def callApi(tmp):
	gpt_api_url = "https://api.openai.com/v1/chat/completions"
	api_key = "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"
	# tmp ="Give me some feedback about my writing ielts here."+ "The pie charts compare home ownership and renting for 1991 and 2007 in percentage terms. In 1991, homeowners were the most popular type of housing, accounting for 60%, or more than over half of all homes. The next largest sector was social rented homes, amounting to 23% or nearly one-third of homes. The remaining homes were mostly privately rented (11%) with a tiny fraction being social housing (6%).Sixteen years later, in 2007, the number of home owners had risen to 70%, or almost three quarters of all homes. This was an increase of 10% compared with 1991. Much of the increase in home ownership can be explained by the decrease in social rented homes, which had dropped from 23% to 17%.The percentage of privately rented homes had remained unchanged at 11%. However, there were 5 million more homes in 2007 compared with 1991 so the number of rented homes had increased despite the same percentage. Social housing has decreased three-fold from 6% in 1991 to 2% in 2007, and it remains the least popular type of housing."
	request_body = {
	    "model": "gpt-3.5-turbo",
	    "messages": [
	        {
	            "role": "system",
	            "content": "You are a helpful Ielts teacher. "
	        },
	        {
	            "role": "user",
	            "content":tmp
	        }
	    ],
	    "stream": True
	}

	headers = {
	    "Content-Type": "application/json",
	    "Authorization": f"Bearer {api_key}"
	}

	response = requests.post(gpt_api_url, json=request_body, headers=headers)

	result = ""
	if response.status_code == 200:
		chunks = [chunk[6:] for chunk in response.text.split("\n") if chunk.strip()]

		for chunk in chunks:
			try:
				data = json.loads(chunk)

				if 'choices' in data and len(data['choices'])>0:
					content = data['choices'][0]['delta'].get('content')
					result+=content+" "
			except:
				pass

	else:
	    print("Request failed with status code:", response.status_code)
	    print("Response:", response.text)
	return result

if __name__ == "__main__":
	tmp = "Give me some feedback about my writing ielts here.  The pie charts compare home ownership and renting for 1991 and 2007 in percentage terms. In 1991, homeowners were the most popular type of housing, accounting for 60%, or more than over half of all homes. The next largest sector was social rented homes, amounting to 23% or nearly one-third of homes. The remaining homes were mostly privately rented (11%) with a tiny fraction being social housing (6%).Sixteen years later, in 2007, the number of home owners had risen to 70%, or almost three quarters of all homes. This was an increase of 10% compared with 1991. Much of the increase in home ownership can be explained by the decrease in social rented homes, which had dropped from 23% to 17%.The percentage of privately rented homes had remained unchanged at 11%. However, there were 5 million more homes in 2007 compared with 1991 so the number of rented homes had increased despite the same percentage. Social housing has decreased three-fold from 6% in 1991 to 2% in 2007, and it remains the least popular type of housing."
	print(callApi(sys.argv[1]))