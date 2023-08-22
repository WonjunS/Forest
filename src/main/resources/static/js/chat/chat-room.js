/**
 * chat-room.js
 */
document.addEventListener('DOMContentLoaded', () => {
	
	const createChatRoom = (e) => {
		e.preventDefault();
		
		const name = document.querySelector('input#name').value;
		
		if(name === '') {
			alert('이름을 입력해주세요.');
			return false;
		}
		
		const form = document.querySelector('form#create-form');
		
		form.action='/chat/room/create';
		form.method='post';
		form.submit();
	};
	
	const createBtn = document.querySelector('button#createBtn');
	createBtn.addEventListener('click', createChatRoom);
	
	const createChatList = (data) => {
		console.log(data);
		
		let htmlStr = '';
		
		for(let room of data) {
			htmlStr += `
				<tr>
					<th scope="row">${room.id}</th>
					<td><a href="/chat/room/${room.id}">${room.name}</a></td>
					<td>${room.creator.nickname}</td>
					<td>${room.modifiedTime}</td>
				</tr>
			`;
		}
		
		document.querySelector('tbody#chatroom-list').innerHTML = htmlStr;
	};
	
	const searchChatRoom = async () => {
		const keyword = document.querySelector('input#keyword').value;
		
		const url = `/api/v1/chat/getList?keyword=${keyword}`;
		const response = await axios.get(url);
		
		createChatList(response.data);
	};
	
	const searchBtn = document.querySelector('button#searchBtn');
	searchBtn.addEventListener('click', searchChatRoom);
	
	const clear = async () => {
		document.querySelector('input#keyword').value = '';
		
		const keyword = document.querySelector('input#keyword').value;
		
		const url = `/api/v1/chat/getList?keyword=${keyword}`;
		const response = await axios.get(url);
		
		createChatList(response.data);
	};
	
	const clearBtn = document.querySelector('button#clearBtn');
	clearBtn.addEventListener('click', clear);
	
});