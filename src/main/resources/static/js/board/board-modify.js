/**
 * board-modify.js
 */
document.addEventListener('DOMContentLoaded', () => {
	
	/**
	 * 게시판 관리자 권한 뺏기
	 */
	const revoke = (e) => {
		const userId = e.target.getAttribute('data-user-id');
		const boardId = e.target.getAttribute('data-board-id');
		
		console.log(`userId = ${userId}, boardId = ${boardId}`);
		
		const result = confirm('랜드의 관리자 권한을 뺏으시겠습니까?');
		if(!result) {
			return false;
		}
		
		const url = '/api/v1/board/revoke';
		const data = { userId, boardId };
		
		axios.put(url, data)
			.then((response) => {
				console.log(response);
				
				location.reload();
			})
			.catch((error) => {
				console.log(error);
			});
	};
	
	const revokeBtns = document.querySelectorAll('button.revokeBtn');
	for(let btn of revokeBtns) {
		btn.addEventListener('click', revoke);
	}
	
	const modify = () => {
		const form = document.querySelector('form#modify-form');
		
		const imageInput = document.querySelector('input#imageFile');
		
		if(imageInput.files.length == 0) {
			alert('변경된 사항이 없습니다.');
		} else {
			console.log('modify');
			form.action='/board/modify';
			form.method='post';
			form.submit();
		}
	};
	
	const modifyBtn = document.querySelector('button#modifyBtn');
	modifyBtn.addEventListener('click', modify);
	
	const loadImage = () => {
		console.log('loadImage');
		
		const preview = document.getElementById('image-show');
		
		const input = document.querySelector('input#imageFile');
	
		if(input.files.length == 0) {
			// 입력된 이미지 파일이 없으면 미리보기 창을 지움
			preview.innerHTML = '';
		} else {
			// 사용자가 입력한 이미지 파일을 보여주는 div 영역을 추가
			const file = input.files[0];   
		    const imgSrc = URL.createObjectURL(file);     
		    const htmlStr = `
		    	<img src="${imgSrc}" id="preview" class="img w-100">
			`;
		
		    preview.innerHTML = htmlStr;
	    }
	};
	
	const imageFile = document.querySelector('input#imageFile');
	imageFile.addEventListener('change', loadImage);
	
	
	const toggleFile = () => {
		const toggle = document.querySelector('input#toggleBtn');
		const status = toggle.getAttribute('data-switch');
		
		const fileArea = document.querySelector('div#file-section');
		
		if(status == 'off') {
			console.log('on');
			
			toggle.setAttribute('data-switch', 'on');
			fileArea.style.display = 'block';
		} else {
			console.log('off');
			
			toggle.setAttribute('data-switch', 'off');
			fileArea.style.display = 'none';
		}
	};
	
	const toggleBtn = document.querySelector('input#toggleBtn');
	toggleBtn.addEventListener('click', toggleFile);
	
	const toggleList = () => {
		const toggle = document.querySelector('input#toggleBtn2');
		const status = toggle.getAttribute('data-switch');
		
		const userList = document.querySelector('div#user-list');
		
		if(status == 'off') {
			console.log('on');
			
			toggle.setAttribute('data-switch', 'on');
			userList.style.display = 'block';
		} else {
			console.log('off');
			
			toggle.setAttribute('data-switch', 'off');
			userList.style.display = 'none';
		}
	};
	
	const toggleBtn2 = document.querySelector('input#toggleBtn2');
	toggleBtn2.addEventListener('click', toggleList);
	
	
	const blockUser = (e) => {
		console.log(e.target);
		
		var userId = e.target.getAttribute('data-id');
		const boardId = document.querySelector('input#boardId').value;
		
		const url = '/api/v1/board/blockById';
		const data = { userId, boardId };
		
		axios.put(url, data)
			.then((response) => {
				console.log(response);
				
				// 블랙 리스트를 불러옴
				const url1 = `/api/v1/board/getBlackList/${boardId}`;
				axios.post(url1)
					.then((response) => {
						console.log(response);
						
						loadBlackList(response);
					})
					.catch((error) => {
						console.log(error);
					});
				
				// 사용자 리스트를 불러옴
				var userId = document.querySelector('input#boardOwnerId').value;
				
				const url2 = `/api/v1/board/getUserList`;
				const data2 = { userId, boardId };
				
				axios.post(url2, data2)
					.then((response) => {
						console.log(response);
						
						loadUserList(response);
					})
					.catch((error) => {
						console.log(error);
					});
			})
			.catch((error) => {
				console.log(error);
			});
	};
	
	const blockBtns = document.querySelectorAll('button.blockBtn');
	for(let btn of blockBtns) {
		btn.addEventListener('click', blockUser);
	}
	
	const loadBlackList = (rsp) => {
		console.log(rsp.data);
		
		const blockUsers = document.querySelector('div#blockUsers');
		let htmlStr = '';
		
		for(let user of rsp.data) {
			htmlStr += `
				<div class="row">
	    			<div class="col-9">${user.nickname}</div>
	    			<div class="col-3">
	    				<button type="button" class="btn btn-outline-dark blockCancelBtn" data-id="${user.boardId}" 
	    					data-user-id="${user.userId}">해제</button>
	    			</div>
	    		</div>
			`;
		}
		
		let listLength = rsp.data.length;
		
		const message = document.querySelector('h5#blocklist-message');
		message.innerText = `차단 리스트 (${listLength})`;
		
		blockUsers.innerHTML = htmlStr;
		
		const blockCancelBtns = document.querySelectorAll('button.blockCancelBtn');
		for(let btn of blockCancelBtns) {
			btn.addEventListener('click', cancelBlock);
		}
	};
	
	const loadUserList = (rsp) => {
		console.log(rsp.data);
		
		const blockList = document.querySelector('ul#block-list');
		blockList.innerHTML = '';
		
		let htmlStr = '';
		for(let user of rsp.data) {
			htmlStr += `
				<li class="list-group-item">
		    		<div class="row">
		    			<div class="col-9">
		    				${user.nickname}
		    			</div>
		    			<div class="col-3 justify-content-end">
		    				<button type="button" class="btn btn-outline-dark blockBtn" data-id="${user.id}">차단</button>
		    			</div>
		    		</div>
	    		</li>
			`;
		}
		
		blockList.innerHTML = htmlStr;
		
		const blockBtns = document.querySelectorAll('button.blockBtn');
		for(let btn of blockBtns) {
			btn.addEventListener('click', blockUser);
		}
	};
	
	const cancelBlock = (e) => {
		const boardId = e.target.getAttribute('data-id');
		const userId = e.target.getAttribute('data-user-id');
		
		const url = '/api/v1/board/cancelBlock';
		const data = { boardId, userId };
		
		axios.put(url, data)
			.then((response) => {
				console.log(response);
				
				// 블랙 리스트를 불러옴
				const url1 = `/api/v1/board/getBlackList/${boardId}`;
				axios.post(url1)
					.then((response) => {
						console.log(response);
						
						loadBlackList(response);
					})
					.catch((error) => {
						console.log(error);
					});
				
				// 사용자 리스트를 불러옴
				var userId = document.querySelector('input#boardOwnerId').value;
				
				const url2 = `/api/v1/board/getUserList`;
				const data2 = { userId, boardId };
				
				axios.post(url2, data2)
					.then((response) => {
						console.log(response);
						
						loadUserList(response);
					})
					.catch((error) => {
						console.log(error);
					});
			})
			.catch((error) => {
				console.log(error);
			});
	};
	
	const blockCancelBtns = document.querySelectorAll('button.blockCancelBtn');
	for(let btn of blockCancelBtns) {
		btn.addEventListener('click', cancelBlock);
	}
	
});


