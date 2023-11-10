<script>
    document.getElementById('submitOrderForm').addEventListener('click', function() {
    var pickupDate = document.getElementById('pickupDate').value;
    if (pickupDate === '') {
    alert('픽업 날짜를 선택해주세요.');
} else {
    document.getElementById('orderForm').submit();
}
});
</script>

<script>
    document.getElementById('submitOrderForm').addEventListener('click', function() {
    var pickupTime = document.getElementById('pickupTime').value;
    if (pickupTime === '') {
    alert('픽업 날짜를 선택해주세요.');
} else {
    document.getElementById('orderForm').submit();
}
});
</script>