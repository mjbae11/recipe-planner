const form = document.querySelector("#recipe-form");
const deleteBtn = document.querySelector()
function submitHandler (event) {
    try {
        event.preventDefault();
        alert("Recipe Saved Successfully!");
        form.submit();
    } catch (error) {
        console.error("Something went wrong: " + error);
        alert("Something went wrong!");
    }
}

form.addEventListener("submit", submitHandler)