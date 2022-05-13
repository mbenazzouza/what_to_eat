import mysql.connector


class Database:

    def __init__(self, host, user, password, database):
        self.host = host
        self.user = user
        self.password = password
        self.database = database

    def set_up_connection(self):
        mydb = mysql.connector.connect(
            host=self.host,
            user=self.user,
            password=self.password,
            database=self.database
        )
        return mydb

    # insert method from database
    def insert(self, query, values):
        mydb = self.set_up_connection()
        mycursor = mydb.cursor()

        mycursor.execute(query, values)

        mydb.commit()

    # select method from database
    def select(self, query, values):
        mydb = self.set_up_connection()
        mycursor = mydb.cursor()

        mycursor.execute(query, values)

        results = mycursor.fetchall()

        return results
