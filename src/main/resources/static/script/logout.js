import {fetchLogin, page} from "./script.js";
import {login_form_body} from "./login_form_body.js";

export async function scriptLogout() {
    window.history.pushState({}, "", "/")
    await fetch("/logout")
        .then(() => {
                const div = document.createElement('div')
                div.className = 'root'
                page.appendChild(div)

                const child_div = document.createElement('div')
                child_div.className = 'block mr-auto ml-auto mt-5 w-25'
                div.appendChild(child_div)

                const h4 = document.createElement('h4')
                h4.className = 'header font-weight-bold'
                h4.textContent = 'Please sign in'
                child_div.appendChild(h4)

                const failLoginDiv = document.createElement('div')
                failLoginDiv.id = 'fail_msg'
                child_div.appendChild(failLoginDiv)

                const form = document.createElement("form")
                form.innerHTML = login_form_body()

                child_div.appendChild(form)
                fetchLogin(form)
            }
        )

}