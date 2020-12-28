import axios from 'axios'

const instance = axios.create({
	baseURL: 'http://localhost:8090/',
})

function getBoardList(params) {
	return instance.get('/board/list', params)
}

function getBoardDetail(params) {
	return instance.get('/board/detail', params)
}

function insertBoard(params) {
	return instance.post('/board/insert', null, params)
}

function updateBoard(params) {
	return instance.post('/board/update', null, params)
}

function deleteBoard(params) {
	return instance.post('/board/delete', null, params)
}

export { getBoardList, getBoardDetail, insertBoard, updateBoard, deleteBoard }
