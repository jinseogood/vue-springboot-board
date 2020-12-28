<template>
	<v-snackbar v-model="sb.show" :color="sb.color" :timeout="3000">
		{{ sb.msg }}
		<template v-slot:action="{ attrs }">
			<v-btn color="white" text v-bind="attrs" @click="sb.show = false">
				<v-icon dark dense>mdi-close-circle-outline</v-icon>
			</v-btn>
		</template>
	</v-snackbar>
</template>

<script>
import { mapState } from 'vuex'
export default {
	computed: {
		...mapState(['sb']),
	},
}
</script>
