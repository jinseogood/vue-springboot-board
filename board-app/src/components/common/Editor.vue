<template>
	<div>
		<Editor
			ref="toastEditor"
			initialEditType="wysiwyg"
			height="500px"
			previewStyle="vertical"
		/>
	</div>
</template>

<script>
import { Editor } from '@toast-ui/vue-editor'
import 'codemirror/lib/codemirror.css' // codemirror style
import '@toast-ui/editor/dist/toastui-editor.css' // Editor style

export default {
	components: {
		Editor,
	},
	methods: {
		getContent() {
			return this.$refs.toastEditor.invoke('getMarkdown')
		},
		setContent(content) {
			this.$refs.toastEditor.invoke('setMarkdown', content)
		},
	},
}
</script>
