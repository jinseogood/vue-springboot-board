<template>
	<div id="weather">
		<div v-if="typeof weather.main != 'undefined'">
			<div :title="weather.weather[0].main">
				<v-icon v-if="code == 800">{{ icons[5] }}</v-icon>
				<v-icon v-else-if="code.substr(0, 1) == 2">
					{{ icons[0] }}
				</v-icon>
				<v-icon v-else-if="code.substr(0, 1) == 3">
					{{ icons[1] }}
				</v-icon>
				<v-icon v-else-if="code.substr(0, 1) == 5">
					{{ icons[2] }}
				</v-icon>
				<v-icon v-else-if="code.substr(0, 1) == 6">
					{{ icons[3] }}
				</v-icon>
				<v-icon v-else-if="code.substr(0, 1) == 7">
					{{ icons[4] }}
				</v-icon>
				<v-icon v-else>
					{{ icons[6] }}
				</v-icon>
			</div>
			<div>{{ Math.round(temp) }}℃</div>
		</div>
		<div v-else>
			<div>
				<v-icon>mdi-cancel</v-icon>
			</div>
			<div>
				{{ weather.cod }}
			</div>
		</div>
	</div>
</template>
<script>
export default {
	data: function() {
		return {
			api_key: 'bfa25eb139e80dd05a980f051b05ea20',
			url_base: 'https://api.openweathermap.org/data/2.5/',
			weather: {},
			code: '',
			temp: 0,
			icons: [
				'mdi-weather-lightning-rainy', // 2xx : Thunderstorm
				'mdi-weather-rainy', // 3xx : Drizzle
				'mdi-weather-pouring', // 5xx : Rain
				'mdi-weather-snowy', // 6xx : Snow
				'mdi-weather-fog', // 7xx : Atmosphere
				'mdi-weather-sunny', // 800 : Clear
				'mdi-weather-cloudy', // 8xx : Clouds
			],
		}
	},
	mounted() {
		let fetchUrl = `${this.url_base}weather?q=Seoul&units=metric&APPID=${this.api_key}`
		fetch(fetchUrl)
			.then(response => {
				return response.json()
			})
			.then(result => {
				this.weather = result
				this.temp = result.main.temp
				this.code = result.weather[0].id.toString()
			})
	},
}
</script>

<style scoped>
#weather {
	font-size: 0.875rem;
	font-weight: 500;
}
</style>
