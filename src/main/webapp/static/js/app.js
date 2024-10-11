document.addEventListener('DOMContentLoaded', () => {
    const amountInput = document.getElementById('amount');
    const durationInput = document.getElementById('duration');
    const monthlyInput = document.getElementById('monthly');
    const projectSelect = document.getElementById('project');
    const professionSelect = document.getElementById('profession');

    const step1Form = document.getElementById('step-1-form');
    const step2Form = document.getElementById('step-2-form');
    const step3Form = document.getElementById('step-3-form');
    const recapSection = document.querySelector('.recap-content');
    const recapContainer = document.querySelector('.recap');

    const step1Indicator = document.getElementById('step-1-indicator');
    const step2Indicator = document.getElementById('step-2-indicator');
    const step3Indicator = document.getElementById('step-3-indicator');

    const amountRange = document.getElementById('amount-range');
    const durationRange = document.getElementById('duration-range');
    const monthlyRange = document.getElementById('monthly-range');

    const formGroup = document.querySelector('.form-group');
    const monthlyLabel = document.createElement('div');
    monthlyLabel.classList.add('monthly-label');
    formGroup.appendChild(monthlyLabel);

    const interestRate = 0.05;

    function calculateMonthly(amount, duration) {
        if (!amount || !duration) return 0;
        const monthlyRate = interestRate / 12;
        const monthly = (amount * monthlyRate * Math.pow(1 + monthlyRate, duration)) / (Math.pow(1 + monthlyRate, duration) - 1);
        return isNaN(monthly) || monthly <= 0 ? 0 : monthly.toFixed(2);
    }

    function updateMonthlyLabel(value) {
        monthlyLabel.textContent = `${value} DH`;
        const rangeWidth = monthlyRange.offsetWidth;
        const thumbPosition = (monthlyRange.value - monthlyRange.min) / (monthlyRange.max - monthlyRange.min) * rangeWidth;
        monthlyLabel.style.left = `${thumbPosition}px`;
    }

    function syncInputs(input, range, callback) {
        input.addEventListener('input', () => {
            range.value = input.value;
            callback();
        });
        range.addEventListener('input', () => {
            input.value = range.value;
            callback();
        });
    }

    function updateDurationBasedOnMonthly() {
        const amount = parseFloat(amountInput.value);
        const monthly = parseFloat(monthlyInput.value);
        const monthlyRate = interestRate / 12;

        if (monthly <= 0 || amount <= 0) {
            durationInput.value = 0;
            durationRange.value = 0;
            return;
        }

        const duration = Math.log(monthly / (monthly - amount * monthlyRate)) / Math.log(1 + monthlyRate);
        durationInput.value = Math.round(duration);
        durationRange.value = durationInput.value;
    }

    function updateMonthlyWhenAmountOrDurationChanges() {
        const amount = parseFloat(amountInput.value);
        const duration = parseFloat(durationInput.value);
        const monthlyValue = calculateMonthly(amount, duration);

        if (monthlyValue) {
            monthlyInput.value = monthlyValue;
            monthlyRange.value = monthlyValue;
            updateMonthlyLabel(monthlyValue);
        }
    }

    syncInputs(amountInput, amountRange, updateMonthlyWhenAmountOrDurationChanges);
    syncInputs(durationInput, durationRange, updateMonthlyWhenAmountOrDurationChanges);
    syncInputs(monthlyInput, monthlyRange, () => {
        monthlyInput.value = monthlyRange.value;
        updateDurationBasedOnMonthly();
    });

    updateMonthlyWhenAmountOrDurationChanges();

    const loanForm = document.getElementById('loan-form');
    loanForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const amountValue = amountInput.value;
        const durationValue = durationInput.value;
        const monthlyValue = monthlyInput.value;
        const selectedProject = projectSelect.options[projectSelect.selectedIndex].text;
        const selectedProfession = professionSelect.options[professionSelect.selectedIndex].text;

        recapSection.innerHTML = `
            <h4 class="recap-first p">Mon Projet</h4>
            <p class="recap-project p">${selectedProject}</p><br><br>
            <h4 class="recap-title p">Détail de mon crédit</h4>
            <p class="recap-profession p s"><strong>Vous êtes:</strong> <span class="recap-value">${selectedProfession}</span></p>
            <hr>
            <p class="recap-amount p s"><strong>Montant:</strong> <span class="recap-value">${amountValue} DH</span></p>
            <hr>
            <p class="recap-duration p s"><strong>Durée:</strong> <span class="recap-value">${durationValue} mois</span></p>
            <hr>
            <p class="recap-monthly p s"><strong>Mensualités:</strong> <span class="recap-value">${monthlyValue} DH</span></p>
        `;

        step1Form.style.display = 'none';
        step2Form.style.display = 'block';
        recapContainer.style.display = 'block';

        step1Indicator.style.backgroundColor = '#02AFBC';
        step1Indicator.classList.remove('active');
        step2Indicator.classList.add('active');
    });

    const contactForm = document.getElementById('contact-form');
    contactForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const emailValue = document.getElementById('email').value;
        const phoneValue = document.getElementById('phone').value;

        recapSection.innerHTML += `
            <h4 class="recap-perso p">Coordonnées et infos personnelles</h4>
            <p class="recap-email p s"><strong>Email:</strong> <span class="recap-value">${emailValue}</span></p>
            <p class="recap-phone p s"><strong>Téléphone:</strong> <span class="recap-value">${phoneValue}</span></p>
        `;

        step2Form.style.display = 'none';
        step3Form.style.display = 'block';
        recapContainer.style.display = 'block';

        step2Indicator.style.backgroundColor = '#02AFBC';
        step2Indicator.classList.remove('active');
        step3Indicator.classList.add('active');
    });

    const creditRadioGroup = document.querySelectorAll('input[name="credits"]');
    const additionalInputsContainer = document.getElementById('additional-inputs');
    const additionalInput1 = document.getElementById('additional-input1');
    const additionalInput2 = document.getElementById('additional-input2');

    creditRadioGroup.forEach(radio => {
        radio.addEventListener('change', () => {
            if (radio.value === 'Oui') {
                additionalInputsContainer.style.display = 'block';
                additionalInput1.required = true;
                additionalInput2.required = true;
            } else {
                additionalInputsContainer.style.display = 'none';
                additionalInput1.value = '';
                additionalInput2.value = '';
                additionalInput1.required = false;
                additionalInput2.required = false;
            }
        });
    });

    function showFlashMessage(message, type) {
        const flashMessageContainer = document.createElement('div');
        flashMessageContainer.textContent = message;
        flashMessageContainer.className = `flash-message ${type}`;

        flashMessageContainer.style.position = 'fixed';
        flashMessageContainer.style.top = '20px';
        flashMessageContainer.style.right = '20px';
        flashMessageContainer.style.zIndex = '1000';
        flashMessageContainer.style.padding = '10px';
        flashMessageContainer.style.color = 'white';
        flashMessageContainer.style.backgroundColor = type === 'error' ? 'red' : 'green';
        flashMessageContainer.style.borderRadius = '5px';
        flashMessageContainer.style.opacity = '0.9';

        document.body.appendChild(flashMessageContainer);

        setTimeout(() => {
            document.body.removeChild(flashMessageContainer);
        }, 5000);
    }

    window.addEventListener('load', () => {
        const flashMessage = sessionStorage.getItem('flashMessage');
        if (flashMessage) {
            showFlashMessage(flashMessage, 'success');
            sessionStorage.removeItem('flashMessage');
        }
    });

    const finalSubmitButton = document.getElementById('final-submit-button');
    finalSubmitButton.addEventListener('click', (e) => {
        e.preventDefault();

        const step3FormElement = step3Form.querySelector('form');

        if (!step3FormElement.checkValidity()) {
            console.log('Step 3 Form is invalid');
            step3FormElement.reportValidity();
            return;
        }

        const data = {
            amount: amountInput.value,
            duration: durationInput.value,
            monthly: monthlyInput.value,
            project: projectSelect.options[projectSelect.selectedIndex].text,
            profession: professionSelect.options[professionSelect.selectedIndex].text,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
            civilite: document.querySelector('input[name="civilite"]:checked').value,
            nom: document.getElementById('nom').value,
            prenom: document.getElementById('prenom').value,
            CIN: document.getElementById('CIN').value,
            datenaissance: document.getElementById('datenaissance').value,
            datedembauche: document.getElementById('datedembauche').value,
            totalrevenue: document.getElementById('totalrevenue').value,
            credits: document.querySelector('input[name="credits"]:checked').value,
            creditImmo: additionalInputsContainer.style.display !== 'none' ? additionalInput1.value : null,
            otherCredits: additionalInputsContainer.style.display !== 'none' ? additionalInput2.value : null,
        };

        const urlEncodedData = new URLSearchParams(data).toString();

        fetch('/Simulator', {
            method: 'POST',
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: urlEncodedData,
        })
            .then(response => response.json())
            .then(result => {
                if (result.status === 'success') {
                    sessionStorage.setItem('flashMessage', 'Your request was successfully submitted!');
                    // location.reload();
                } else {
                    sessionStorage.setItem('flashMessage', result.message || 'An error occurred while processing your request.');
                    showFlashMessage(result.message || 'An error occurred while processing your request.', 'error');
                }
            })
            .catch(error => {
                console.error('Error: ', error);
                sessionStorage.setItem('flashMessage', 'An unexpected error occurred. Please try again later.');
                showFlashMessage('An unexpected error occurred. Please try again later.', 'error');
            });
    });

});
