from gtts import gTTS
import os

def download_audio(word, language='en'):
    tts = gTTS(text=word, lang=language, slow=False)
    audio_path = f'audio/{word}.mp3'
    tts.save(audio_path)
    return audio_path

# if __name__ == "__main__":
    # word_to_download = ""  # Thay "hello" bằng từ bạn muốn lấy âm thanh
    # audio_file = download_audio(word_to_download)

#     print(f"Audio file '{audio_file}' downloaded.")


file = open(r'data\EngtoV.txt','r',encoding = 'utf8')
data = file.read()
data = data.split("\n")
for i in range(100):
    myList = data[i].split("<token>")
    print(myList[1])
    word_to_download = myList[1]  # Thay "hello" bằng từ bạn muốn lấy âm thanh
    audio_file = download_audio(word_to_download)
