$(document).ready(function () {
    getTableOfUsers();
    userAuthMethod();
    getAllRoles();
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
                userData += `<tr>
                <td>${user.id}</td>
                <td>${user.first_name}</td>
                <td>${user.last_name}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${roles}</td>
                <td> <button type="button" id="updateButton" class="btn btn-info"
                data-toggle="modal" data-target="#updateModal" data-id="${user.id}">Edit</button> </td>
                
                <!--
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  Запустить модальное окно
</button>-->
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


$(document).on("click", "#updateButton", function () {
    const id = $(this).data('id');
    $.ajax({
        url: '/adminRest/getUserById/' + id,
        success: function (user) {
            $('#id_update').val(user.id);
            $('#first_name_update').val(user.first_name);
            $('#last_name_update').val(user.last_name);
            $('#age_update').val(user.age);
            $('#email_update').val(user.email);
            $.each(allRoles, (i,role) => {
                $("#rolesInputEdit").append(
                    $("<option>").text(role.role)
                );
            });

        },
        error: function () {
            alert("Error with get User By id");
        }
    })

})

$(document).on("click", "#update", function () {
    /*const selectUser = $('#roleUpdate option:selected');
    const roleTemp = selectUser.val();
    let role = "USER";
    if (roleTemp == 1) {
        role = "ADMIN"
    }*/
    let updateUser = {
        id: $('#id_update').val(),
        first_name: $('#first_name_update').val(),
        last_name: $('#last_name_update').val(),
        age: $('#age_update').val(),
        email: $('#email_update').val(),
        password: $('#password_update').val(),
        authorities: $('#rolesInputEdit').val()

        /*roles: [
            {
                "id": roleTemp,
                "role": role
            }
        ]*/
    };
    $.ajax({
        url: '/adminRest/updateUser',
        type: 'POST',
        data: JSON.stringify(updateUser),
        dataType: 'json',
        contentType: "application/json",
        success: function () {
            $('.close').click();
            getTableOfUsers()
        },
        error: function () {
            alert("ошибка")
        }
    })
})

let allRoles;
function getAllRoles() {
    $.ajax({
        url: "/adminRest/getAllRoles",
        type: "GET",
        dataType: "json"
    }).done((msg) => {
        allRoles = JSON.parse(JSON.stringify(msg));
    })
}













/*$(document).on("click", "#update", function () {
    const selectUser = $('#rolesFromH_update option:selected');
    const roleTemp = selectUser.val();
    let role = "USER";
    if (roleTemp == 1) {
        role = "ADMIN"
    }
    let userToUpdate = {
        id: $('#id_update').val(),
        first_name: $('#first_name_update').val(),
        last_name: $('#last_name_update').val(),
        age: $('#age_update').val(),
        email: $('#email_update').val(),
        password: $('#password_update').val(),
        roles: [
            {
                "id": roleTemp,
                "role": role
            }
        ]
    }
    $.ajax({
        url: '/adminRest/updateUser',
        type: 'POST',
        data: JSON.stringify(userToUpdate),
        contentType: "application/json",
        success: function (user) {
            getTableOfUsers();
            $('.close').click();
        },
        error: function () {
            alert("Error with Update Button");
        }
    })
})*/