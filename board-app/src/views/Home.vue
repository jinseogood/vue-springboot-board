<template>
	<div>
		<v-container>
			<v-card class="mx-auto" max-width="500" tile>
				<v-card-title>
					Vue, Spring Boot Board
				</v-card-title>
				<v-card-text>
					<h2>Introduction</h2>
					<br />
					Basic bulletin board function and comment function provide additional
					weather and time.
					<br />
					<br />
					<h2>Development Environment</h2>
					<br />
					<h3>APP</h3>
					<ul>
						<li>Vue</li>
						<li>Vuetify</li>
						<li>Vuex</li>
						<li>Vue-Router</li>
						<li>Axios</li>
						<li>Toast UI Editor, Viewer</li>
						<li>OpenWeatherMap</li>
					</ul>
					<br />
					<h3>API</h3>
					<ul>
						<li>SpringBoot</li>
						<li>MyBatis</li>
						<li>Docker</li>
						<li>MySQL</li>
					</ul>
				</v-card-text>
				<v-img height="100%" src="../assets/programmer.png">
					<v-row align="end" class="fill-height" style="margin: 0;">
						<v-col class="pa-1">
							<v-list-item color="rgba(0, 0, 0, .4)" dark class="pa-1">
								<v-list-item-content>
									<v-list-item-title class="title">
										<a href="mailto:sun7190@naver.com">
											<v-icon>mdi-email</v-icon>
										</a>
										<a href="https://dev-jsk.tistory.com" target="_blank">
											<v-icon>mdi-blogger</v-icon>
										</a>
										<a
											href="https://github.com/jinseogood/vue-springboot-board"
											target="_blank"
										>
											<v-icon>mdi-github</v-icon>
										</a>
										<br />
										Jinseo Kim
									</v-list-item-title>
									<v-list-item-subtitle>Programmer</v-list-item-subtitle>
								</v-list-item-content>
							</v-list-item>
						</v-col>
					</v-row>
				</v-img>
			</v-card>
		</v-container>
	</div>
</template>

<script>
// @ is an alias to /src

export default {
	name: 'Home',
	components: {},
}
</script>

<style scoped>
a {
	text-decoration: none;
}
</style>
