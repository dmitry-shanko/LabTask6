function checkStock() {

	notInStockElem = document.getElementById('notInStock');
	priceElem = document.getElementById('price');

	if (notInStockElem.checked == true) {
		priceElem.disabled = true;
	} else {
		priceElem.disabled = false;
	}
}

function priceNotInStockValidate(index) 
{
	correctFieldColor = 'WHITE';
	notInStockElem = document.getElementsByClassName('notInStock')[index];
	priceElem = document.getElementsByClassName('price')[index]; 
	if (notInStockElem.checked == true) 
	{
		priceElem.style.backgroundColor = correctFieldColor;
		priceElem.disabled = true;
	} 
	else 
	{
		priceElem.disabled = false;
	}
}

function updateValidate(productsNumber) 
{
	fieldsValid = true;
	nameRegExp = new RegExp('^([a-zA-Z0-9\\s]+)$');
	providerRegExp = new RegExp('^([a-zA-Z0-9\\s]+)$');
	colorRegExp = new RegExp('^([a-zA-Z0-9]+)$');
	modelRegExp = new RegExp('^([a-zA-Z0-9]+)$');
	priceRegExp = new RegExp('^(([0-9]+)|([0-9]+[.][0-9]+))$');
	dateRegExp = new RegExp('^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]((19|20)[0-9]{2})$');
	
	errorMessages = [];
	incorrectFieldColor = 'RED';
	correctFieldColor = 'WHITE';

	for ( var i = 0; i < productsNumber; i++) 
	{
		nameElem = document.getElementsByClassName('name')[i];
		providerElem = document.getElementsByClassName('provider')[i];
		modelElem = document.getElementsByClassName('model')[i];
		dateElem = document.getElementsByClassName('date')[i];
		colorElem = document.getElementsByClassName('color')[i]; 
		notInStockElem = document.getElementsByClassName('notInStock')[i]; 
		priceElem = document.getElementsByClassName('price')[i]; 
/*		nameElem = $('.name')[i];
		providerElem = $('.provider')[i];
		modelElem = $('.model')[i];
		dateElem = $('.date')[i];
		colorElem = $('.color')[i];
		notInStockElem = $('.notInStock')[i];
		priceElem = $('.price')[i];*/

		lineNumber = i + 1;

		if (!nameRegExp.test(nameElem.value)) 
		{
			errorMessages.push('Incorrect "Name" at line ' + lineNumber	+ '. It should contains letters and numbers.');
			nameElem.style.backgroundColor = incorrectFieldColor;
		} 
		else 
		{
			nameElem.style.backgroundColor = correctFieldColor;
		}
		if (!providerRegExp.test(providerElem.value)) 
		{
			errorMessages.push('Incorrect "Provider" at line ' + lineNumber	+ '. It should contains letters and numbers.');
			providerElem.style.backgroundColor = incorrectFieldColor;
		}
		else
		{
			providerElem.style.backgroundColor = correctFieldColor;
		}
		if (!modelRegExp.test(modelElem.value)) 
		{
			errorMessages.push('Incorrect "Model" at line ' + lineNumber + '. It should contains letters and numbers.');
			modelElem.style.backgroundColor = incorrectFieldColor;
		} 
		else 
		{
			modelElem.style.backgroundColor = correctFieldColor;
		}
		if (!dateRegExp.test(dateElem.value)) 
		{
			errorMessages.push('Incorrect "Date" at line ' + lineNumber	+ '.It should be formatted as dd-MM-yyyy.');
			dateElem.style.backgroundColor = incorrectFieldColor;
		} 
		else 
		{
			dateElem.style.backgroundColor = correctFieldColor;
		}
		if (!colorRegExp.test(colorElem.value)) 
		{
			errorMessages.push('Incorrect "Color" at line ' + lineNumber + '. It should contains letters and numbers.');
			colorElem.style.backgroundColor = incorrectFieldColor;
		} 
		else 
		{
			colorElem.style.backgroundColor = correctFieldColor;
		}
		if (notInStockElem.checked == false) 
		{
			if (!priceRegExp.test(priceElem.value))
			{
				errorMessages.push('Incorrect "Price" at line ' + lineNumber + '. It should contains only digits.');
				priceElem.style.backgroundColor = incorrectFieldColor;
			} 
			else 
			{
				priceElem.style.backgroundColor = correctFieldColor;
				priceElem.value = accounting.formatNumber(priceElem.value, 2, '', '.');
			}
		}
	}
	if (errorMessages.length != 0) 
	{
		fieldsValid = false;
		list = document.getElementById('errors');
		while (list.hasChildNodes()) 
		{
			list.removeChild(list.firstChild);
		}
		for ( var j = 0; j < errorMessages.length; j++) 
		{
			li = document.createElement('LI');
			li.innerHTML = errorMessages[j];
			list.appendChild(li);
		}
	}
	return fieldsValid;
}