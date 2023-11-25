import mysql.connector

# Thông tin kết nối đến MySQL
mydb = mysql.connector.connect(
    host="sql.freedb.tech",
    user="freedb_tienhoa",
    password="kGDwFjf6$7Vjyr7",
    database="freedb_tienhoa"
)

# Kiểm tra kết nối thành công hay không
if mydb.is_connected():
    print("Kết nối thành công!")

# Tạo một cursor để thực hiện các truy vấn SQL
mycursor = mydb.cursor()

# Ví dụ truy vấn SELECT
mycursor.execute("SELECT * FROM Accounts;")

# Lấy kết quả của truy vấn
result = mycursor.fetchall()

# In ra kết quả
for row in result:
    print(row)

# Đóng kết nối
mycursor.close()
mydb.close()
