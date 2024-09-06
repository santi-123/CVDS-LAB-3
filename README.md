# Laboratorio 3 cvds 

## Integrantes:

- David Felipe Velasquez Contreras
- Santiago Diaz Rojas

1. Creamos el proyecto de maven y añadimos las propiedades:
![images/img.png](images/img.png)
![images/img_1.png](images/img_1.png)

2. Creamos la distribucion del proyecto
![images/img_2.png](images/img_2.png)

3. Ahora hacemos lo mismo pero en la carpeta de test
![images/img_3.png](images/img_3.png)

4. Validamos que el proyecto compile.
![images/img_3.png](images/img_4.png)
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
![images/img_5.png](images/img_5.png)
7. Implementacion de metodos:
   1. addBook
   ![images/img_6.png](images/img_6.png)
   2. loanABook
   ![images/img_7.png](images/img_7.png)
   3. returnLoan
   ![images/img_8.png](images/img_8.png)
8. Validacion de aceptacion de pruebas:
   ![images/img_9.png](images/img_9.png)
9. Validacion con integracion de Jacoco:
   1. Se logra una cobertura del 88%
   ![images/img_10.png](images/img_10.png)    
   2. Si ingresamos a validar que parte de nuestro codigo tiene cobertura:
   ![images/img_11.png](images/img_11.png)
   ![images/img_13.png](images/img_13.png)
   ![images/img_14.png](images/img_14.png)
10. Validacion de integracion en Sonar Qube. Al momento de integrar esta herremienta a nuestro proyecrto generamos un token el cual nos permite revisar de manera online, el analisis estatico de nuestro codigo:
![images/img_17.png](images/img_17.png)
![images/img_16.png](images/img_16.png)
    - Como se puede ver se logra una covertura del 83.8%, lo que nos asegura que se esta cubriendo gran parte de nuesto proyecto con pruebas.