$(document).ready(function () {
    getTableOfUsers();
    userAuthMethod();
    getTableOfUser();
});

function userAuthMethod() {
    $.ajax({
        url: '/adminRest/userAuth',
        success: function (user) {
            const userRoles = user.authorities;
            let roles = '';
            for (let u of userRoles) {
                roles += u.role + " ";
            }

            let userTemp = `<strong>
                <span>${user.email}</span></strong>
                <small>with roles: <span>${roles}</span></small>`;

            $('#UserAuth').html(userTemp);
        },
        error: function () {
            alert("Error with User Auth");
        }
    })

}

function getTableOfUsers() {
    $.ajax({
        url: '/adminRest/allUsers',
        success: function (listOfUsers) {
            let userData = '';
            $.each(listOfUsers, function (i, user) {
                let userRoles = user.authorities;
                let roles = '';

                for (let role of userRoles) {
                    roles += role.role + " ";
                }
                localStorage.setItem('user'+ user.id, JSON.stringify({id : user.id, firstName : user.first_name, lastName : user.last_name, age : user.age, email : user.email}));

                userData += `<tr>
                <td>${user.id}</td>
                <td>${user.first_name}</td>
                <td>${user.last_name}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${roles}</td>
                <td> <button type="button" id="updateButton" class="btn btn-info"
                data-toggle="modal" data-target="#updateModal" data-id="${user.id}">Edit</button> </td>
                
                <td> <button type="button" id="deleteButton" class="btn btn-danger" 
                    data-toggle="modal" data-target="#deleteModal" data-id="${user.id}">Delete</button> </td></tr>`;
            });
            $('#tableUsers').html(userData);
        },
        error: function () {
            alert("getAllUsers Error");
        }
    })
}

function getTableOfUser() {
    $.ajax({
        url: '/userRest/getUser',
        success: function (user) {
            let userRoles = user.authorities;
            let roles = '';
            for (let role of userRoles) {
                roles += role.role + " ";
            }

            let userData = `<tr>
                <td>${user.id}</td>
                <td>${user.first_name}</td>
                <td>${user.last_name}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${roles}</td></tr>`;

            $('#tableUser').html(userData);

        },
        error: function () {
            alert("get User Error");
        }
    })
}

/*Update modal*/
$(document).on("click", "#updateButton", function () {
    const id = $(this).data('id');
    let user = JSON.parse(localStorage.getItem('user'+ id));
    $('#id_update').val(user.id);
    $('#first_name_update').val(user.firstName);
    $('#last_name_update').val(user.lastName);
    $('#age_update').val(user.age);
    $('#email_update').val(user.email);
});

/*Delete modal*/
$(document).on("click", "#deleteButton", function () {
    const id = $(this).data('id');
    let user = JSON.parse(localStorage.getItem('user'+ id));
    $('#id_delete').val(user.id);
    $('#first_name_delete').val(user.firstName);
    $('#last_name_delete').val(user.lastName);
    $('#age_delete').val(user.age);
    $('#email_delete').val(user.email);
});

$("#delete").on('click', (e) => {
    e.preventDefault();

    let id = $('#id_delete').val();

    $.ajax({
        url: '/adminRest/deleteUser',
        type: 'DELETE',
        data: JSON.stringify(id),
        dataType: 'json',
        contentType: "application/json",
        success: function () {
            $('#deleteModal').click();
            getTableOfUsers();
        },
        error: function () {
            alert("ошибка")
        }
    });
});

function getRoles(roles){
    let role = [];

    if (roles.length === 2) {
        role.push("ADMIN");
        role.push("USER");
    } else {
        if (roles.val() === "ADMIN") {
            role.push("ADMIN");
        } else {
            role.push("USER");
        }
    }
    return role;
}
$("#update").on('click', (e) => {
    // e.preventDefault();
    const selectUser = $('#rolesFromH_update option:selected');


    /*let role = [];

    if (selectUser.length === 2) {
        role.push("ADMIN");
        role.push("USER");
    } else {
        if (selectUser.val() === "ADMIN") {
            role.push("ADMIN");
        } else {
            role.push("USER");
        }
    }*/

    let user = {
        id: $('#id_update').val(),
        first_name: $('#first_name_update').val(),
        last_name: $('#last_name_update').val(),
        age: $('#age_update').val(),
        email: $('#email_update').val(),
        password: $('#password_update').val(),
        roles: getRoles(selectUser)
    };

    $.ajax({
        url: '/adminRest/updateUser',
        type: 'PUT',
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: "application/json",
        success: function () {
            $('#updateModal').click();
            getTableOfUsers();
        },
        error: function () {
            alert("ошибка")
        }
    });
});

/*Add*/
$("#add").on('click', (e) => {
    // e.preventDefault();

    const selectUser = $('#rolesFromH_add option:selected');
    /*let role = [];

    if (selectUser.length === 2) {
        role.push("ADMIN");
        role.push("USER");
    } else {
        if (selectUser.val() === "ADMIN") {
            role.push("ADMIN");
        } else {
            role.push("USER");
        }
    }*/

    let user = {
        id: $('#id_add').val(),
        first_name: $('#first_name_add').val(),
        last_name: $('#last_name_add').val(),
        age: $('#age_add').val(),
        email: $('#email_add').val(),
        password: $('#password_add').val(),
        roles: getRoles(selectUser)
    };


    $.ajax({
        url: '/adminRest/addUser',
        type: 'PUT',
        data: JSON.stringify(user),
        dataType: 'json',
        contentType: "application/json",
        success: function () {
            document.getElementById('formAdd').reset();
            getTableOfUsers();
            $('#menu-page-admin a:first').tab('show');
        },
        error: function () {
            alert("ошибка")
        }
    });
});