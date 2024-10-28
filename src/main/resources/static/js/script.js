// Toggle Navigation Menu
const navBar = document.querySelector("nav"),
      menuBtn = document.querySelectorAll(".menu-icon"),
      overlay = document.querySelector(".overlay");

// Toggle the menu open/close when clicking on menu button
menuBtn.forEach((btn) => {
    btn.addEventListener("click", () => {
        navBar.classList.toggle("open");
    });
});

// Close the menu when clicking on the overlay
if (overlay) {
    overlay.addEventListener("click", () => {
        navBar.classList.remove("open");
    });
}

// Form Validation
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form');

    form.addEventListener("submit", function(event) {
        const firstName = document.getElementById("firstname").value.trim();
        const lastName = document.getElementById("lastname").value.trim();
        const specialization = document.getElementById("specialization").value.trim();
        const email = document.getElementById("email").value.trim();
        const phone = document.getElementById("phone").value.trim();
        const experience = document.getElementById("experience").value.trim();
        const address = document.getElementById("address").value.trim();

        if (!firstName || !lastName || !specialization || !email || !phone || !experience || !address) {
            event.preventDefault();
            alert("Please fill out all fields before submitting.");
        } else if (isNaN(experience) || experience <= 0) {
            event.preventDefault();
            alert("Please enter a valid number of years for experience.");
        } else if (!validateEmail(email)) {
            event.preventDefault();
            alert("Please enter a valid email address.");
        } else if (!validatePhone(phone)) {
            event.preventDefault();
            alert("Please enter a valid phone number.");
        }
    });

    // Email Validation Function
    function validateEmail(email) {
        const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        return emailPattern.test(email);
    }

    // Phone Number Validation Function
    function validatePhone(phone) {
        const phonePattern = /^[0-9]{10}$/;
        return phonePattern.test(phone);
    }
});
