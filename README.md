# CalculatorApp

Button Mapping:
  - Numbers/ Decimal: 
    - "0" -> 0
    - "1" -> 1
    - "2" -> 2
    - "3" -> 3
    - "4" -> 4
    - "5" -> 5
    - "6" -> 6
    - "7" -> 7
    - "8" -> 8
    - "9" -> 9
    - "." -> Decimal
  - Operations: 
    - "=" -> Eqauls
    - "+" -> Addition
    - "-" -> Subtraction
    - "*" -> Multiplication
    - "/" -> Division

Fields:
  - Operation field (TextView):
    - Is not editable and reflects the last operation the user selected.
  - Enter Number field (EditText):
    - Is editable and reflects the numbers the user selects. This field only allows one decimal to be entered.
  - Result field (EditText):
    - Is not editable and reflects the result of the number(s) entered within the Enter Number field.

User Input:
  - The user can populate the Enter Number field with the keyboard provided or with the tablet's soft keyboard.
  
Screen Orientation and Saving State:
  - The calculator can be used in either portrait or landscape and the user can change the device's orientation at anytime.
  - If the device's orientation changes, any data within the Operation, Enter Number, and Result field will remain. 

Basic Functionality:
  - A user can populate the Enter Number field with any positive number or zero by selecting the corresponding buttons.
  - If the Result field is blank, and the Enter Number field is populated with a number, if the user selects any operation,
  the number within the Enter Number field will populate the Result field and the Enter Number field will become blank and
  the cursor will be focused within the field ready for the user to enter the next number.
  - If the Result field is not blank, and the Enter Number field is also not blank, if the user selects any operation, the 
  operation that was displayed within the Operation field will be performed and the result will be depicted within the Result field.
  - An operation can be changed at anytime and the Operation field will reflect the last operation selected by the user.
  - The user cannot divide by zero. When this scenario occurs, the number within the Result field remains and the Edit Number
  field is cleared. Additionally, a toast message displays notifying the user that they cannot divide by zero.

Clear Functionality:
  - Currently, the Clear button does not work.
  - Selecting the "=" operation will result in the calculator being cleared; however, visually, the number will still remain in the 
  Result field until a number is entered in the Enter Number field, followed by the user selecting an operation to be performed. 

SDK Info:
  - Compile SDK Version = 26
  - Min SDK Version = 17
  - Target SDK Version = 26
