import {page} from "./script.js";

export async function scriptLogout() {
    console.log("logout")
    window.history.pushState({}, "", "/")
    await fetch("http://localhost:8080/logout")
        .then(res => console.log(res))
        .then(()=> fetch("/pages/login_form.html")
            .then((data) => data.text())
            .then(data=>page.innerHTML = data)
        )
}
