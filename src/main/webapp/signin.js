let username = document.getElementById('username');
let password = document.getElementById('password');
let submit = document.getElementById('submit');
let errormsg = document.getElementById('error-msg');

submit.addEventListener("click", (e) => {
	e.preventDefault()
    let name = username.value.trim();
    let psw = password.value.trim();

    fetch('/BusTicketBooking/SignIn', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: name,
            password: psw
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("HTTP error " + response.status);
        }
        return response.json();
    })
    .then(data => {
        if (data.status === "signin successfully") {
            location.replace("/BusTicketBooking/Home.html")
        }
    })
    .catch(() => {
		errormsg.textContent = "Invalid Username/Password"
    });
});
