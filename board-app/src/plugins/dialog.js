const dialog = {}

dialog.install = function(Vue) {
	/**
	 * 확인창 표시
	 * @param {*} title
	 * @param {*} text
	 */
	Vue.prototype.confirmDialog = function(title, text) {
		let res = this.$dialog.confirm({
			title: title,
			text: text,
			persistent: true,
			actions: {
				false: 'No',
				true: {
					color: 'red',
					text: 'Yes',
				},
			},
		})

		return res
	}
	/**
	 * 입력창 표시
	 * @param {*} title
	 * @param {*} text
	 */
	Vue.prototype.promptDialog = function(title, text, content) {
		let res = this.$dialog.prompt({
			title: title,
			text: text,
			value: content,
			persistent: true,
		})

		return res
	}
}

export default dialog
