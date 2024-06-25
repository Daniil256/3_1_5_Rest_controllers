import {render_users} from "./render_users.js";
import {disableRef} from "../script.js";

export async function scriptAdmin() {
    await getUsers()

    async function getUsers() {
        await fetch("/api/admin")
            .then(res => Promise.resolve(res.json())
                .then(data => render_users(data)))
    }

    const reg_form = document.querySelector("#reg_form")
    reg_form.addEventListener('submit', async e => {
        e.preventDefault()
        const formData = new FormData(reg_form)
        await fetch("/api/admin/registration", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                'firstname': formData.get('firstname'),
                'lastname': formData.get('lastname'),
                'age': formData.get('age'),
                'email': formData.get('email'),
                'password': formData.get('password'),
                'roles': formData.get('roles')
            })
        })
            .then(() => getUsers())
        $('#list-admin').tab('show');
        const users = document.querySelector('#btn-show-users')
        const reg = document.querySelector('#btn-show-reg')
        reg.className = 'list-group-item border-0'
        users.className = 'list-group-item active border-0'
    })

    const form_edit = document.querySelector("#form_edit")
    form_edit.addEventListener('submit', async e => {
        e.preventDefault()
        const formData = new FormData(form_edit)

        await fetch("/api/admin/edit", {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                'id': formData.get('id'),
                'firstname': formData.get('firstname'),
                'lastname': formData.get('lastname'),
                'age': formData.get('age'),
                'email': formData.get('email'),
                'password': formData.get('password'),
                'roles': formData.get('roles')
            })
        })
            .then(() => getUsers())
        $('#edit_modal').modal('hide');
    })

    const del_form = document.querySelector("#form_del")
    del_form.addEventListener('submit', async e => {
        e.preventDefault()
        const formData = new FormData(del_form)

        await fetch("/api/admin/delete", {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                'id': formData.get('id'),
            })
        })
            .then(() => getUsers())
        $('#delete_modal').modal('hide');
    })

    disableRef()
}


