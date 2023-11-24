document.getElementById('submitOrderForm').addEventListener('click', function(event) {
    var pickupDate = document.getElementById('pickupDate').value;
    var pickupTime = document.getElementById('pickupTime').value;
    var flowerOptions = document.querySelectorAll('input[name="flowertype"]');
    var isFlowerSelected = Array.from(flowerOptions).some(option => option.checked);

    if (pickupDate === '') {
        alert('픽업 날짜를 선택해주세요.');
        event.preventDefault();
    } else if (pickupTime === '') {
        alert('픽업 시간을 선택해주세요.');
        event.preventDefault();
    } else if (!isFlowerSelected) {
        alert('주문하실 꽃을 선택해주세요.');
        event.preventDefault();
    } else {
        document.getElementById('orderForm').submit();
    }
});
