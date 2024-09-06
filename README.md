# Laboratorio 3 cvds 

## Integrantes:

- David Felipe Velasquez Contreras
- Santiago Diaz Rojas

1. Creamos el proyecto de maven y añadimos las propiedades:
![img.png](img.png)
![img_1.png](img_1.png)

2. Creamos la distribucion del proyecto
![img_2.png](img_2.png)

3. Ahora hacemos lo mismo pero en la carpeta de test
![img_3.png](img_3.png)

4. Validamos que el proyecto compile.
![img_3.png](img_4.png)
5. Ahora procedemos a crear las pruebas, por cada prueba vamos a hacer un commit y luego de las pruebas implementaremos metodo por metodo.
   - addBookSuccessfully
   - addBookIncreasesQuantityWhenBookAlreadyExists
   - addMultipleDifferentBooks
   - addBookFailsIfBookIsNull
   - addBookAndVerifyCorrectAmountAfterLoan
   - loanBookSuccessfully
   - loanFailsIfBookNotAvailable
   - loanFailsIfUserDoesNotExist
   - loanFailsIfUserAlreadyHasActiveLoanForSameBook
   - loanDateIsSetCorrectly
   - returnLoanWithNullLoan
   - returnLoanWhenLoanIsNotActive
   - returnActiveLoan
   - returnNonExistentLoan
   - returnLoanAlreadyReturned
6. Validacion de no aceptacion de pruebas:
![img_5.png](img_5.png)
7. Implementacion de metodos:
   1. addBook
   ![img_6.png](img_6.png)
   2. loanABook
   ![img_7.png](img_7.png)
   3. returnLoan
   ![img_8.png](img_8.png)
8. Validacion de aceptacion de pruebas:
   ![img_9.png](img_9.png)
9. Validacion con integracion de Jacoco:
   1. Se logra una cobertura del 88%
   ![img_10.png](img_10.png)    
   2. Si ingresamos a validar que parte de nuestro codigo tiene cobertura:
   ![img_11.png](img_11.png)
   ![img_13.png](img_13.png)
   ![img_14.png](img_14.png)
10. Validacion de integracion en Sonar Qube. Al momento de integrar esta herremienta a nuestro proyecrto generamos un token el cual nos permite revisar de manera online, el analisis estatico de nuestro codigo:
![img_17.png](img_17.png)
![img_16.png](img_16.png)
    - Como se puede ver se logra una covertura del 83.8%, lo que nos asegura que se esta cubriendo gran parte de nuesto proyecto con pruebas.