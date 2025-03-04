import os

class Student:
    def __init__(self, roll_no, name, fee, contact_no):
        self.roll_no = roll_no
        self.name = name
        self.fee = fee
        self.contact_no = contact_no

def display(students):
    print("\nRoll No     |Name           |Fees       |Contact No")
    print("-" * 50)
    for student in students:
        print(f"{student.roll_no:<12} | {student.name:<13} | {student.fee:<10} | {student.contact_no}")
    print("\nTotal Count of registered Students:", len(students))

def save_students(filename, students):
    try:
        with open(filename, "w") as file:
            file.write("Roll No     |Name           |Fees       |Contact No\n")
            file.write("-" * 50 + "\n")
            for student in students:
                file.write(f"{student.roll_no:<12} | {student.name:<13} | {student.fee:<10} | {student.contact_no}\n")
        print("Student records saved successfully.")
    except Exception as e:
        print("Error occurred while saving data:", e)

def load_students(filename):
    students = []
    try:
        with open(filename, "r") as file:
            next(file) 
            for line in file:
                if '|' not in line:
                    continue 
                roll_no, name, fee, contact_no = line.strip().split('|')
                students.append(Student(int(roll_no.strip()), name.strip(), float(fee.strip()), int(contact_no.strip())))
    except FileNotFoundError:
        open(filename, 'w').close()
    except Exception as e:
        print("Error occurred while loading data:", e)
    return students

def is_roll_no_unique(students, roll_no):
    for student in students:
        if student.roll_no == roll_no:
            return False
    return True

def register_student(students):
    print("\nEnter the following details to register:\n")
    while True:
        roll_no = int(input("Enter Roll No.: "))
        if is_roll_no_unique(students, roll_no):
            break
        else:
            print("Roll number already exists. Please enter a unique roll number.")

    name = input("Enter Name of Student: ").strip()
    fee = float(input("Enter Fees: "))
    contact_no = int(input("Enter Contact No.: "))
    students.append(Student(roll_no, name, fee, contact_no))
    print("Student registered successfully.")

def search_student(students):
    ch = int(input("if you want to serach with roll no Press 1, If you want to search with name press 2: "))
    if ch==1:
        roll_no = int(input("Enter roll no: "))
        found1 = False
        for student in students:
             if roll_no == student.roll_no:
                 print("\nStudent Details:")
                 print(f"Roll No     : {student.roll_no}")
                 print(f"Name        : {student.name}")
                 print(f"Fees        : {student.fee}")
                 print(f"Contact No  : {student.contact_no}")
                 found1 = True
             break
        if not found1:
         print("\nStudent Details are not available, Please enter a valid Roll No.!!")
    elif ch==2:
         nm = input("Enter name: ")
         found = False
         for student in students:
             if nm == student.name:
                 print("\nStudent Details:")
                 print(f"Roll No     : {student.roll_no}")
                 print(f"Name        : {student.name}")
                 print(f"Fees        : {student.fee}")
                 print(f"Contact No  : {student.contact_no}")
                 found = True
             break
         if not found:
          print("\nStudent Details are not available, Please enter a Name!!")




def edit_student(students):
    roll_no = int(input("\nEnter the student Roll No. to EDIT the details: "))
    found = False
    for student in students:
        if roll_no == student.roll_no:
            found = True
            print("\nEdit student Details: ")
            print("1)Student Roll no.")
            print("2)Student Name")
            print("3)Contact No.")
            print("4)GO BACK")
            ch = input("Enter your choice: ")
            if ch == "1":
                student.roll_no = int(input("\nEnter new Student Roll No.: "))
            elif ch == "2":
                student.name = input("Enter new Student Name: ")
            elif ch == "3":
                student.contact_no = int(input("Enter new Student Contact No.: "))
            elif ch == "4":
                break
            else:
                print("\nEnter the correct choice:")
    if not found:
        print("\nStudent Details are not available, Please enter a valid Roll No.!!")

def delete_student(students):
    roll_no = int(input("\nEnter Student Roll No. to DELETE the record: "))
    found = False
    for student in students:
        if roll_no == student.roll_no:
            students.remove(student) 
            print("Student record deleted successfully.")
            found = True
            break
    if not found:
        print("\nStudent Details are not available, Please enter a valid Roll No.!!")

def main():
    file_path = os.path.join(os.getcwd(), "student_records.txt")
    students = load_students(file_path)

    while True:
        print("\n********Welcome to the Student Registration********\n")
        print("1) Register new student\n"
              "2) Search for Student\n"
              "3) Edit Student details\n"
              "4) Delete Student record\n"
              "5) Display all registered Students\n"
              "6) Exit\n")
        choice = input("Enter your choice: ")

        if choice == "1":
            register_student(students)

        elif choice == "2":
            search_student(students)

        elif choice == "3":
            edit_student(students)

        elif choice == "4":
            delete_student(students)

        elif choice == "5":
            display(students)

        elif choice == "6":
            save_students(file_path, students)
            print("\nYou have chosen Exit!! saving the Files and closing tool.\nThank You!!")
            break

        else:
            print("\nEnter a correct choice from the List:")


main()
