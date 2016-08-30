/**
 * Created by mahsa on 30/08/2016.
 */
function editRow(rowNumber) {
    var firstName = document.getElementById("firstName" + rowNumber).innerHTML;
    var lastName = document.getElementById("lastName" + rowNumber).innerHTML;
    var fatherName = document.getElementById("fatherName" + rowNumber).innerHTML;
    var birthDate = document.getElementById("birthDate" + rowNumber).innerHTML;
    var nationalCode = document.getElementById("nationalCode" + rowNumber).innerHTML;
    var number = document.getElementById("number" + rowNumber).innerHTML;
    document.location.href = 'edit?firstName=' + firstName + '&lastName=' + lastName + '&fatherName=' + fatherName + '&birthDate=' + birthDate +
        '&nationalCode=' + nationalCode + '&number=' + number + '&rowNumber=' + rowNumber;
}

function deleteRow(rowNumber) {
    document.location.href = 'delete?rowNumber=' + rowNumber;
    }