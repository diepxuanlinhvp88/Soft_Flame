import sqlite3
conn = sqlite3.connect("dict_hh.db")
cursor = conn.cursor()
cursor.execute("SELECT * FROM av;")
data = cursor.fetchall()
print(data)