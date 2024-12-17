import csv
from pymongo import MongoClient

# Connect to MongoDB
client = MongoClient("mongodb://localhost:27017/")
db = client["device"]
collection = db["devices"]

# Read CSV and insert data
with open("test_data.csv", "r") as file:
    reader = csv.DictReader(file)
    for row in reader:
        document = {
                "customId": int(row["id"]),
                "batteryPower": int(row["battery_power"]),
                "bluetooth": int(row["blue"]),
                "clockSpeed": float(row["clock_speed"]),
                "dualSim": int(row["dual_sim"]),
                "frontCamera": int(row["fc"]),
                "fourG": int(row["four_g"]),
                "internalMemory": int(row["int_memory"]),
                "mobileDepth": float(row["m_dep"]),
                "mobileWeight": int(row["mobile_wt"]),
                "numCores": int(row["n_cores"]),
                "primaryCamera": int(row["pc"]),
                "pixelHeight": int(row["px_height"]),
                "pixelWidth": int(row["px_width"]),
                "ram": int(row["ram"]),
                "screenHeight": int(row["sc_h"]),
                "screenWidth": int(row["sc_w"]),
                "talkTime": int(row["talk_time"]),
                "threeG": int(row["three_g"]),
                "touchScreen": int(row["touch_screen"]),
                "wifi": int(row["wifi"]),
                "priceRange": -1 
            }
        collection.insert_one(document)

print("Data imported successfully!")
