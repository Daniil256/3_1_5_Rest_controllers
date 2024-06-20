import {render_users} from "./render_users.js";
import {handleLocation} from "../script.js";


export default async function scriptAdmin() {

    console.log("data")
    await fetch("http://localhost:8080/admin")
        .then(res => Promise.resolve(res.json())
            .then(data => render_users(data[0])))

    const reg_form = document.querySelector("#reg_form")
    reg_form.addEventListener('submit', async e => {
        e.preventDefault()
        console.log("REG")
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
            .then(async (res) => console.log(res))
    })

    const form_edit = document.querySelector("#form_edit")
    form_edit.addEventListener('submit', async e => {
        e.preventDefault()
        console.log("EDIT")
        const formData = new FormData(form_edit)

        await fetch("/admin/edit", {
            method: 'POST',
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
            .then(async (res) => console.log(res))
            .then(() => scriptAdmin())
    })
    document.querySelectorAll(".no_ref").forEach(el=>
        el.addEventListener("click", (event) => {
        console.log("route")
        event = event || window.event
        event.preventDefault()
        window.history.pushState({}, "", event.target.href)
        handleLocation()
    }))
}


