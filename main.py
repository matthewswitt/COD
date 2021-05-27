import requests
import time

# matthewswitt was here


# url = "https://call-of-duty-modern-warfare.p.rapidapi.com/multiplayer/switty_6/psn"
# headers = {"X-RapidAPI-Key": "f2cd3d7a13msh7fe7e4fdceb3854p111d61jsn13afecec8f1c", "X-RapidAPI-Host":"call-of-duty-modern-warfare.p.rapidapi.com"}
#
# response = requests.get(url, headers=headers)
# data = response.json()
# print(data)
# print(data["level"])

def get_info(mode, username, device):
    url = "https://call-of-duty-modern-warfare.p.rapidapi.com"+"/"+mode+"/"+username+"/"+device
    headers = {"X-RapidAPI-Key": "f2cd3d7a13msh7fe7e4fdceb3854p111d61jsn13afecec8f1c", "X-RapidAPI-Host":"call-of-duty-modern-warfare.p.rapidapi.com"}
    response = requests.get(url, headers=headers)
    data = response.json()

    return data

print(get_info("multiplayer", "reider6in09", "psn"))
time.sleep(2)
print(get_info("multiplayer", "switty_6", "psn"))