document.addEventListener('DOMContentLoaded', () => {
    const amountInput = document.getElementById('amount');
    const amountRange = document.getElementById('amount-range');
    const durationInput = document.getElementById('duration');
    const durationRange = document.getElementById('duration-range');
    const monthlyInput = document.getElementById('monthly');
    const monthlyRange = document.getElementById('monthly-range');

    function syncInputs(input, range) {
        input.addEventListener('input', () => {
            range.value = input.value;
            calculateMonthly();
        });
        range.addEventListener('input', () => {
            input.value = range.value;
            calculateMonthly();
        });
    }

    function calculateMonthly() {
        const amount = parseFloat(amountInput.value);
        const duration = parseFloat(durationInput.value);
        const interestRate = 0.05;
        const monthlyRate = interestRate / 12;
        const monthly = (amount * monthlyRate * Math.pow(1 + monthlyRate, duration)) / (Math.pow(1 + monthlyRate, duration) - 1);
        monthlyInput.value = monthly.toFixed(2);
        monthlyRange.value = monthly;
    }

    syncInputs(amountInput, amountRange);
    syncInputs(durationInput, durationRange);
    calculateMonthly();

    document.getElementById('loan-form').addEventListener('submit', function (e) {
        e.preventDefault();
        alert(`Form submitted! Amount: ${amountInput.value} DH, Duration: ${durationInput.value} months, Monthly Payment: ${monthlyInput.value} DH`);
    });
});
