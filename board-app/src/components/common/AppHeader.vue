<template>
	<v-app-bar app color="blue-grey darken-1" dark>
		<v-btn @click="movePage('/home')" text>
			<span class="mr-2">Home</span>
		</v-btn>
		<v-btn @click="movePage('/list')" text>
			<span class="mr-2">Board</span>
		</v-btn>
		<v-spacer></v-spacer>
		<Clock />
		<v-divider class="mx-4" inset vertical></v-divider>
		<Weather />
	</v-app-bar>
</template>

<script>
import Clock from '@/components/common/Clock'
import Weather from '@/components/common/Weather'

export default {
	name: 'AppHeader',
	components: {
		Clock,
		Weather,
	},
}
</script>
