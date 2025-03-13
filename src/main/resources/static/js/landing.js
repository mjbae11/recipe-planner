
document.addEventListener('DOMContentLoaded', function() {
    const footerLinks = document.querySelectorAll('a[href*="/policy"], a[href*="/terms"], a[href*="/about"], a[href*="/contact"], a[href*="/info"]');

    footerLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            alert('Work in progress! Be ready soon.');
        });
    });
});
