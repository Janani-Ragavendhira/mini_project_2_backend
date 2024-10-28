
//Registration form validation
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');

    form.addEventListener('submit', function(event) {
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        // Simple client-side validation
        if (!validateEmail(email)) {
            alert('Please enter a valid email address.');
            event.preventDefault(); // Prevent form submission
        } else if (password.length < 6) {
            alert('Password must be at least 6 characters long.');
            event.preventDefault(); // Prevent form submission
        }
    });

    function validateEmail(email) {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Basic email regex
        return regex.test(email);
    }
});



    // login form validation

const loginForm = document.querySelector("#loginForm");
    if (loginForm) {
        loginForm.addEventListener("submit", function (event) {
            const email = document.querySelector("input[name='email']").value;
            const password = document.querySelector("input[name='password']").value;

            if (!email) {
                alert("Email is required.");
                event.preventDefault(); // Prevent form submission
            } else if (!password) {
                alert("Password is required.");
                event.preventDefault(); // Prevent form submission
            }
        });
    }
});
