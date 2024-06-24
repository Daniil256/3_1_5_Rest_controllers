
export function render_users(data){
    document.querySelector("#list-admin").className = 'tab-pane fade w-100 active show'
    document.querySelector('#list-user').className = 'tab-pane fade'


    document.querySelector("#header_data").innerHTML =
        `<b>${data[1].email}</b> with roles ${data[1].rolesName}`

    document.querySelector("#users_table").innerHTML = (
        `<tr class="head font-weight-bold border-bottom border-top">
                                <td>ID</td>
                                <td>First Name</td>
                                <td>Last Name</td>
                                <td>Age</td>
                                <td>Email</td>
                                <td>Role</td>
                                <td>Edit</td>
                                <td>Delete</td>
                            </tr>`
        +
            data[0].map(user => `<tr>
        <td>${user.id}</td>
        <td>${user.firstname}</td>
        <td>${user.lastname}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${user.rolesName}</td>
        <td>
        <button class="btn btn-info text-white btn-sm" 
        onclick="
        $('#edit_modal').modal('show');
        document.querySelector('#id').value = '${user.id}'
        document.querySelector('#id_disabled').value = '${user.id}'
        document.querySelector('#firstname').value = '${user.firstname}'
        document.querySelector('#lastname').value = '${user.lastname}'
        document.querySelector('#age').value = '${user.age}'
        document.querySelector('#email').value = '${user.email}'
        "
        >Edit
        </button>
        </td>
        
        <td>
        <button class="btn btn-danger text-white btn-sm" 
         onclick="
        $('#delete_modal').modal('show');
        document.querySelector('#del_id').value = '${user.id}'
        document.querySelector('#del_id_disabled').value = '${user.id}'
        document.querySelector('#del_firstname').value = '${user.firstname}'
        document.querySelector('#del_lastname').value = '${user.lastname}'
        document.querySelector('#del_age').value = '${user.age}'
        document.querySelector('#del_email').value = '${user.email}'
        "
        >Delete
        </button>
        </td>
    </tr>`).join(""))
}
