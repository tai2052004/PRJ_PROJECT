document.addEventListener('DOMContentLoaded', function () {
    const seats = document.querySelector('.chair');
    const tickets = seats.querySelectorAll('.seat');
    var chosenSeats = [];
    let totalPrice = 0;
    let chosenCount = 0;

    tickets.forEach(ticket => {
        ticket.addEventListener('click', () => {
            if (ticket.classList.contains('seat-selected') || ticket.classList.contains('seat-available-form') || ticket.classList.contains('seat-chosen-form')) {
                return;
            }
            ticket.classList.toggle('seat-chosen');
            if (ticket.classList.contains('seat-chosen')) {
                chosenCount++;
                totalPrice += 50000;
            } else {
                chosenCount--;
                totalPrice -= 50000;
            }
            updateSelectedInfo();
        });
    });
    const bookButton = document.getElementById('book-now-button');
    bookButton.addEventListener('click', function (event) {
        if (chosenCount === 0) {
            Swal.fire({
                icon: 'warning',
                title: '<strong>Chưa chọn ghế</strong>',
                text: 'Vui lòng chọn ít nhất một ghế.',
                customClass: {
                    title: 'swal-title-custom',
                    popup: 'swal-popup-custom'
                }
            });
            event.preventDefault();
        } else {
            window.location.href = 'info.jsp';
        }
    });
    refresh();
    function updateSelectedInfo() {
        const chosenSeatsElement = document.getElementById('chosen-seats');
        const totalAmountElement = document.getElementById('total-price');
        let formattedTotalPrice = totalPrice.toLocaleString('vi-VN');
        chosenSeats = [];
        tickets.forEach((ticket, index) => {
            if (ticket.classList.contains('seat-chosen')) {
                var row = String.fromCharCode(65 + Math.floor(index / 9)); // Chuyển index thành chữ cái A, B, C,...
                var column = (index % 9) + 1; // Số thứ tự cột bắt đầu từ 1
                var seatLabel = row + column;
                chosenSeats.push(seatLabel);
            }
        });

        chosenSeatsElement.textContent = chosenCount > 0 ? `${chosenCount} (${chosenSeats.join(', ')})` : 'None';
        totalAmountElement.textContent = `${formattedTotalPrice}`;
        document.getElementById('chosenSeatsInput').value = chosenSeats.join(',');
        document.getElementById('chosenSeatNumber').value = chosenCount;
        document.getElementById('totalPriceInput').value = formattedTotalPrice;
    }
    function refresh() {
        const refreshButton = document.querySelector('.booking-refresh a');
        refreshButton.addEventListener('click', function () {
            tickets.forEach(ticket => {
                ticket.classList.remove('seat-chosen');
            });
            chosenCount = 0;
            totalPrice = 0;
            updateSelectedInfo();
        });
    }
    refresh();

    const searchButton = document.querySelector('.header-search input');
    if (searchButton) {
        searchButton.addEventListener('keypress', function (event) {
            if (event.key === 'Enter') {
                search();
            }
        });
    }
});
function search() {
    var searchValue = document.querySelector('.header-search input').value;
    if (searchValue.trim() === '') {
        alert('Vui lòng nhập từ khóa tìm kiếm.');
        return;
    }
    window.location.href = "newjsp.jsp";
}
