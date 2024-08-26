let slideIndex = 0;
showSlides();

function showSlides() {
    let i;
    let slides = document.getElementsByClassName("mySlides");
    let dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slideIndex++;
    if (slideIndex > slides.length) {
        slideIndex = 1;
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";
    setTimeout(showSlides, 6000); // Change image every 2 seconds
}


function view(eventId) {
    event.preventDefault();
    const eventContent = {
        1: 'Nhanh tay đặt vé tại đây để được sống lại hồi ức tuổi thơ với "bom tấn" đến từ đạo diễn Nguyễn Quang Dũng, và hưởng ưu đãi có 1-0-2 đến từ Metiz Cinema và Helio Center nhé!',
        2: 'Tháng 9 và 10 này rạp Metiz không chỉ có phim hay, bắp ngon mà còn có ưu đãi đặc biệt giảm đến 15.000 vnđ khi thanh toán qua VNPAY-QR hoặc Ví VNPAY dành cho tất cả khách hàng của Metiz Đà Nẵng.',
        3: 'Mùa lễ Halloween năm nay, khi đến Metiz Cinema xem những bộ phim vào ngày lễ ma quái này còn được nhận những viên kẹo ma quái dễ thương từ Metiz nữa đấy.',
        4: 'Từ lâu, những đôi tất chính là món quà được rất nhiều người lựa chọn dành tặng nhau bởi nó mang ý nghĩa đầy đặn và gửi gắm sự ấm áp cũng như mang tới những phúc lành cho người nhận, vì vậy, với món quà xinh xinh này, sẽ thay Metiz gửi đến bạn lời chúc một mùa Giáng sinh thật an lành, ấm áp và không cô đơn nhé.',
        5: 'Gạt bỏ nỗi ám ảnh đầu tuần bằng năng lượng SIÊU NHÂN và biến thứ Hai trở thành một ngày SIÊU HẠNG cùng những bộ phim hay với giá cực kỳ ưu đãi 45.000 đồng/ vé 2D.'
    };

    document.getElementById('contentevent').innerHTML = eventContent[eventId];
}

//document.addEventListener("click", function (event) {
//    closeForm(event);
//});
//
//function closeForm(event) {
//    var loginForm = document.getElementById("loginForm");
//    var registerForm = document.getElementById("registerForm");
//
//    if (!loginForm.contains(event.target) && !registerForm.contains(event.target)) {
//        loginForm.style.display = "none";
//        registerForm.style.display = "none";
//    }
//}



var formLog = document.getElementById('id01');
var formRe = document.getElementById('id02');
var overlay = document.getElementById('overlay__login');
var drop = document.getElementById('content');
function showRegisterForm() {
    formLog.style.display = "none";
    formRe.style.display = "block";
    overlay.style.display = "block";
    drop.style.display = "none";
}
function showLoginForm() {
    formRe.style.display = "none";
    formLog.style.display = "block";
    overlay.style.display = "block";
    drop.style.display = "none";
}


function showDropContent() {
    drop.style.display = "block";
}

var buttondrop = document.getElementById('button__drop');

window.onclick = function (event) {
    if (event.target === overlay) {
        formRe.style.display = "none";
        formLog.style.display = "none";
        overlay.style.display = "none";
    }
    if (event.target !== buttondrop && drop.style.display === "block") {
        drop.style.display = "none";
    }
};

$('.close').on('click', function() {
  var alertBox = $(this).parent();
  alertBox.removeClass('bounceInRight');
  alertBox.addClass('bounceOutRight');
  alertBox.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
    alertBox.hide();
  });
});
    window.onload = function() {
        const params = new URLSearchParams(window.location.search);
        if (params.has('error')) {
            const alertDiv = document.querySelector('.alerts');
            alertDiv.style.display = 'block';
            moveUp(alertDiv);
        }
    };

    function moveUp(element) {
        let top = parseInt(window.getComputedStyle(element).top);
        function step() {
            if (top > 0) {
                top -= 1; // giảm giá trị top để di chuyển lên trên
                element.style.top = top + 'px';
                requestAnimationFrame(step); // tiếp tục gọi hàm step cho tới khi hoàn tất
            }
        }
        requestAnimationFrame(step);
    }   