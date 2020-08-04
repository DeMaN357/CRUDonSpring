$(document).ready(function () {
    getTableOfUsers();
});

function getTableOfUsers() {
    $.ajax({
        url: '/faq',
        success: function (listOfUsers) {
            let userData = '';
            $.each(listOfUsers, function (i, user) {
                userData += `<tr>
                <td>${user.id}</td>
                <td>${user.first_name}</td>
                <td>${user.last_name}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td> <button type="button" id="updateButton" class="btn btn-info"
                data-toggle="modal" data-target="#updateModal" data-id="${user.id}">Edit</button> </td>
                <td> <button type="button" id="deleteButton" class="btn btn-danger" 
                    data-toggle="modal" data-target="#deleteModal" data-id="${user.id}">Delete</button> </td></tr>`;
            });
            console.log(userData);
            $('#userTable').html(userData);
        },
        error: function (error) {
            alert("getAllUsers Error");
        }
    })
}
