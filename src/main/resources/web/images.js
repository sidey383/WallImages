
class Size {

	constructor(width, height) {
		this.width = width;
		this.height = height;
		if (!height) {
			this.width = width.width;
			this.height = width.height;
		}
	}

	setSize(image) {
		image.width = this.width;
		image.height = this.height;
	}

	resize(maxSize) {
		var resize = Math.max((this.width / maxSize.width), (this.height / maxSize.height), 1);
		this.width /= resize;
		this.height /= resize;
	}
	
}

var imageSRC;
const maxImageSize = new Size(500, 500);
var mapData = {
	x: 0,
	y: 0,
	name: "customImage",
	maps: {}
};
var dataSendURL = "/mapData";

function _base64ToArrayBuffer(base64) {
    var binary_string = window.atob(base64);
    var len = binary_string.length;
    var bytes = new Uint8Array(len);
    for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
    }
    return bytes.buffer;
}

function send() {
	var body = {};
	Object.assign(body, mapData);
	console.log(body);
	for (var key in body.maps) {
		body.maps[key] = window.btoa(String.fromCharCode.apply(null, body.maps[key]));
		console.log(_base64ToArrayBuffer(body.maps[key]));
	}
	xhr = new XMLHttpRequest();
	xhr.open('POST', dataSendURL, true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify(body));
}


function paint() {
	const path = image1.src;
	var image = new Image();
	image.src = URL.createObjectURL(f);
	image.onload = function() {
		mapData.x = document.getElementById("width").value;
		mapData.y = document.getElementById("height").value;
		const size = new Size(image);
		const mapsSize = new Size(mapData.x*128, mapData.y*128);
		size.resize(mapsSize);
		size.setSize(image);
		mapsSize.setSize(helpCanvas);
		const ctx = helpCanvas.getContext('2d');
		ctx.drawImage(image, (mapsSize.width - size.width)/2, (mapsSize.height - size.height)/2, size.width, size.height);
		mapData.maps = {};
		const table = document.getElementById("maps");	
		
		table.innerHTML = "";
		
		
		for(var y = 0; y < mapsSize.height/128; y++) {
			var row = document.createElement("tr");
			for(var x = 0; x < mapsSize.width/128; x++) {
				var cell = document.createElement("td");
				const img = ctx.getImageData(128*x, 128*y, 128, 128);
				var pixels = img.data;
				mapData.maps[x+'.'+y] = toMinecraftColors(pixels);
				pixels = toRealColors(mapData.maps[x+'.'+y]);
				img.data = pixels;
				var canvas = document.createElement("canvas")
				canvas.width = 128;
				canvas.height = 128;
				var ctx2 = canvas.getContext('2d');
				ctx2.putImageData(img, 0, 0, 0, 0, 128, 128);
				cell.appendChild(canvas);
				row.appendChild(cell);
			}
			table.appendChild(row);
		}
	}
}

function toMinecraftColors(pixels) {
	var minecraftColors = new Uint8Array(pixels.length / 4);
	for (var i = 0; i < pixels.length; i += 4) {
		minecraftColors[i / 4] = getColor(pixels[i], pixels[i + 1], pixels[i + 2]);
	}
	return minecraftColors;
}

function toRealColors(minecraftColors) {
	pixels = new Uint8Array(minecraftColors.lenght * 4)
	for (var i = 0; i < pixels.length; i += 4) {
		if (minecraftColors[i / 4] < 4) {
			pixels[i] = pixels[i + 1] = pixels[i + 2] = pixels[i + 3] = 0;
		} else {
			pixels[i + 2] = COLORS[minecraftColors[i / 4] - 4].b;
			pixels[i + 1] = COLORS[minecraftColors[i / 4] - 4].g;
			pixels[i] = COLORS[minecraftColors[i / 4] - 4].r;
		}
	}
	return pixels;
}

function save(files) {
	if (files.length > 0) {
		f = files[0];
		if (f && f.type.split('/')[0] == 'image') {
			imageSRC = f;
			drawOriginal();

		}
	}
}

function drawOriginal() {
	var ctx = image1.getContext('2d');
	var image = new Image();
	image.src = URL.createObjectURL(imageSRC);
	image.onload = function() {
		var size = new Size(image);
		size.resize(maxImageSize);
		size.setSize(image1);
		ctx.drawImage(image, 0, 0, size.width, size.height);
	}
}

let dropArea = document.getElementById('drop-area');

['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
	dropArea.addEventListener(eventName, preventDefaults, false)
})

function preventDefaults(e) {
	e.preventDefault()
	e.stopPropagation()
}

['dragenter', 'dragover'].forEach(eventName => {
	dropArea.addEventListener(eventName, highlight, false)
});
['dragleave', 'drop'].forEach(eventName => {
	dropArea.addEventListener(eventName, unhighlight, false)
});

dropArea.addEventListener('drop', handleDrop, false);

function highlight(e) {
	dropArea.classList.add('highlight')
}

function unhighlight(e) {
	dropArea.classList.remove('highlight')
}

function handleDrop(e) {
	let dt = e.dataTransfer
	let files = dt.files
	save(files);
}




