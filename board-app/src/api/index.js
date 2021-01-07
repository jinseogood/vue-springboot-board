import axios from 'axios'

const instance = axios.create({
	baseURL: 'http://192.168.0.34:8090/',
})

/**
 * 게시글 목록 조회
 * @param {*} params
 */
function getBoardList(params) {
	return instance.get('/board/list', params)
}

/**
 * 게시글 상세 조회
 * @param {*} params
 */
function getBoardDetail(params) {
	return instance.get('/board/detail', params)
}

/**
 * 게시글 작성
 * @param {*} params
 */
function insertBoard(params) {
	return instance.post('/board/insert', null, params)
}

/**
 * 게시글 수정
 * @param {*} params
 */
function updateBoard(params) {
	return instance.post('/board/update', null, params)
}

/**
 * 게시글 삭제
 * @param {*} params
 */
function deleteBoard(params) {
	return instance.post('/board/delete', null, params)
}

/**
 * 댓글 작성
 * @param {*} params
 */
function insertReply(params) {
	return instance.post('/board/reply/insert', null, params)
}

/**
 * 댓글 조회
 * @param {*} params
 */
function getReplyList(params) {
	return instance.get('/board/reply/list', params)
}

/**
 * 댓글 수정
 * @param {*} params
 */
function updateReply(params) {
	return instance.post('/board/reply/update', null, params)
}

/**
 * 댓글 삭제
 * @param {*} params
 */
function deleteReply(params) {
	return instance.post('/board/reply/delete', null, params)
}

export {
	getBoardList,
	getBoardDetail,
	insertBoard,
	updateBoard,
	deleteBoard,
	insertReply,
	getReplyList,
	updateReply,
	deleteReply,
}
