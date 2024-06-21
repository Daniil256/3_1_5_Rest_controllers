import {render_users} from "./render_users.js";
import {disableRef} from "../script.js";


export async function scriptAdmin() {
await getUsers()
    async function getUsers() {
        await fetch("http://localhost:8080/admin")
            .then(res => Promise.resolve(res.json())
                .then(data => render_users(data)))
    }

    const reg_form = document.querySelector("#reg_form")
    reg_form.addEventListener('submit', async e => {
        e.preventDefault()
        const formData = new FormData(reg_form)
        await fetch("/admin/registration", {
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
    })

    const form_edit = document.querySelector("#form_edit")
    form_edit.addEventListener('submit', async e => {
        e.preventDefault()
        const formData = new FormData(form_edit)

        await fetch("/admin/edit", {
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
    })

    const del_form = document.querySelector("#form_del")
    del_form.addEventListener('submit', async e => {
        e.preventDefault()
        const formData = new FormData(del_form)

        await fetch("/admin/delete", {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: new URLSearchParams({
                'id': formData.get('id'),
                'firstname': '',
                'lastname': '',
                'age': '0',
                'email': '',
                'password': '',
                'roles': ''
            })
        })
            .then(() =>  getUsers())
    })

    disableRef()
}


