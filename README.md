

<picture>
  <source media="(prefers-color-scheme: light)" srcset="https://github.com/MahmoudGoda2003/Qcademy/assets/95986689/2f66bf89-b82f-4c88-81a8-41cc1e909a63">
  <source media="(prefers-color-scheme: dark)" srcset="https://github.com/MahmoudGoda2003/Qcademy/assets/95986689/52d18f93-0707-43e3-bac5-1cc4e1e5586b">
  <img alt="Image description (important for accessibility)" src="https://default-image.png"> </picture>

  
## Overview
Qcademy presents a full-stack e-learning platform built with ReactJS for the interactive and user-friendly frontend and Spring Boot for the robust and scalable backend. It caters to both students and teachers, fostering a dynamic learning environment.

Benefits:
Empowers Learners: Students gain access to a diverse range of courses, fostering continuous learning and skill development.
Facilitates Teaching: Teachers have the tools to create and deliver engaging courses, effectively reaching their students.
Scalable and Flexible: The platform can accommodate a growing user base and future feature enhancements.
User-Friendly Interface: Both students and teachers can navigate the platform intuitively, maximizing its learning potential.
This e-learning platform represents a powerful tool for democratizing education and making valuable knowledge more accessible.

## Table of Contents
1. [Documentation](#documentation)
2. [Features](#features)
3. [Installation](#installation)
4. [Usage](#usage)
    - [Schema formate](#schema)
    - [Command-Line Interface](#commands)
5. [FAQs](#faqs)
6. [Contributing](#contributing)
7. [License](#license)
8. [Upcoming Features](#upcoming-features)

## 1. Documentation <a name="documentation"></a>

## 2. Features <a name="features"></a>
1) Sign up for Students and teachers
2) User Authentication and google login
3) Student Homepage
4) Professor / TA Homepage
5) Creating Course
6) Viewing Course Content
7) Managing Course
8) Searching Results
9) Grading Assignments
10) Creating Exams
11) Students Uploading Their Assignments
12) Online Lectures
13) Online Attendance
14) Keeping Track of Student’s Progress in The Course

## 3. Installation <a name="installation"></a>
To use DataHive, follow these installation steps:
1. **Install python**: Python 3.x installed on your machine.

2. **Install DataHive**: Install DataHive package on your local machine:

```shell
pip install DataHive
```
## 4. Usage <a name="usage"></a>

### Schema Format <a name="schema"></a>
Below is an example of the schema format that defines the structure of the database, including the database name and tables with their respective attributes:
   1. **Schema Example**:
      ```
      {
          "database_name" : "ClassA1",
          "Tables" : [
              {
                  "name" : "student",
                  "columns" : ["First_name", "Last_name", "CGPA", "Gender", "Age"],
                  "primary_key"  : "Last_name",
                  "index_keys" : ["First_name", "Last_name", "CGPA"],
                  "overwrite" : "True",
             }
                      ]
       }
      ```
2. **Table Contents**:
  The schema format consists of the following elements for defining tables within the database:

      | Name | Type | Nullable | Notes |
      | ---- | ---- | -------- | ----- |
      | Name | string | No | Name of the table. |
      | Columns | List of Strings | No | List of column names, including the primary key. |
      | Primary_key | String | No | The primary key for the table. |
      | Index_key | List of Strings | Yes | List of column names that serve as index keys. |
      | Overwrite | string | Yes | Initial value is 'False'; you can set its value to 'True' to enable overwriting.|
   
### Command-Line Interface <a name="commands"></a>

*Note: Ensure that you have a JSON file containing your schema before creating the database.*

| Command | Parameters | Description |
|---------|------------|-------------|
| `Create` | `DatabaseSchemaPath` | Creates a database following the specified [schema](#schema).
| `Set` | `DatabaseName`, `TableName`, `InputData` | Sets a row with the provided input data.
| `Get` | `DatabaseName`, `TableName`, `InputQuery` | Utilizes the input query to retrieve specific data from the database.
| `Delete` | `DatabaseName`, `TableName`, `InputQuery` | Uses the input query to delete specific data from the database.
| `Clear` | `DatabaseName` | Resets the specified database to its initial state.

This Command-Line Interface (CLI) provides a set of commands for creating, updating, querying, deleting data, and resetting databases, offering comprehensive control over your data management tasks.

## 5. FAQs <a name="faqs"></a>

- **Q1: What is DataHive?**
  - **A1:** DataHive is a lightweight database system designed for storing data in JSON files. It provides essential CRUD (Create, Read, Update, Delete) operations and ensures synchronization between read and write operations. DataHive is an ideal solution for small-scale applications where a simple and reliable data storage system is required.

- **Q2: How do I install DataHive?**
  - **A2:** To install DataHive, you need to have Python 3.x installed on your machine. After ensuring Python is installed, run the following command:

    ```shell
    pip install DataHive
    ```

- **Q3: What is the schema format for defining the database structure?**
  - **A3:** The schema format defines the structure of the database, including the database name, tables, and their attributes. It consists of elements like table names, column names, primary keys, index keys, and overwriting settings. You can find a detailed example of the schema format in the [Schema Format](#schema) section of this README.

- **Q4: How do I use the Command-Line Interface (CLI) to interact with DataHive?**
  - **A4:** The CLI offers a set of commands for creating, updating, querying, deleting data, and resetting databases. You can use the general command format:

    ```shell
    python DataHive -c [command] [options]
    ```

    Replace `[command]` with the desired database command and `[options]` with relevant command options. Examples of various commands can be found in the [Example Usage](#examples) section.

- **Q5: Is DataHive compatible with Java?**
  - **A5:** Yes, DataHive is cross-language compatible. We have developed a dedicated Java driver (SimpleDBDriver) that seamlessly integrates with DataHive. This Java driver allows Java applications to interact with the database, extending its usability to diverse programming ecosystems.

## 6. Contributing <a name="contributing"></a>

Contributions to Qcademy are welcome! Feel free to fork the repository, make improvements, and create pull requests.

## 7. License <a name="license"></a>

Qcademy is released under the MIT License. See the [LICENSE](https://github.com/MahmoudGoda2003/Qcademy/blob/main/LICENSE) file for details.

## 8. Upcoming Features <a name="upcoming-features">
Stay tuned for future updates and additional features. We are constantly working on enhancing DataHive to provide an even better data storage solution for your needs, including:

   - Granting Certificate of Completion
   - Student’s Timetable
   - Discussion Tab for Each Course
-----


  


