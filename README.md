1. To run this project locally, you should clone it and configure the following environment variables: 

| Evironment variable | Type  | Description | Example |
| --- | --- | --- | --- |
| FILE_PATH | required | The system-dependent file name, or with other words, the absolute path to your local file with loans data. | FILE_PATH=/home/aleks/Downloads/Loans.xlsx |
| DISPLAY_SHORT_RESULT |  optional | Boolean field which tells the system how to display the result data. By default its 'true',  so it would return records with only some fields  visualized.  |To see the list with all data fields included in the result, use: DISPLAY_SHORT_RESULT=false  |
| SORT_BY | optional | Parameter for sorting. Possible values are ASC and DESC  | SORT_BY=DESC |
| SORT_DIRECTION | optional | Parameter for sorting. Possible values TERM and INSTALLMENT  | SORT_DIRECTION=INSTALLMENT |


The application sorts the result by default by  yield in descending order. The first item in the result list is the recommended by the system. 
If  SORT_BY and SORT_DIRECTION  are passed as environment variables, the results will be sorted  **initially by yield** and **secondly by term or installment** - in the given sort direction.  


The program is configured to calculate the "yield", using the compound interest formula. The equation is defined in 
the *CompoundInterestCalculationService* class.  
In the function there, the  **n** argument represents how many times the interest should be received in a given year. 
The interest rate can be compounded monthly, weekly or daily. Those periods  are defined in the app as installment type.
The installment types's number of periods/slots used for the calculation on yearly basis can be configured additionally.  
It is completely optional and should be configured if the user is not satisfied with the default values mentioned 
bellow.
with the default 


| Evironment variable | Default value | Example |
| --- | --- | --- |
|INSTALLMENT_TYPE_DAYS | 365 | INSTALLMENT_TYPE_DAYS=360 |
|INSTALLMENT_TYPE_WEEKS | 53 | INSTALLMENT_TYPE_WEEKS=52 |


2. Code Overview

In the Context class all environment variables are initialized, which are later on used in the Program class to instantiate all needed services for the future program process. This provides simple dependency injection implementation.


Currently the application doesn't strictly check the input file - format and data. 
Make sure that it's named correctly and it should have a proper extension (.csv or . xlsx).
If there are any empty records, or with unexpected format,  those will be simply skipped and not included in the result. Unhandled excptions can also arise in such cases.
The file columnar order should  remain the same as well.  
 
 
The external libraries in this project are reduced to minimum from better performance perspective.
Apache POI - the Java API for Microsoft Documents - is used for importing the input spreadsheet file types in the 
system.
