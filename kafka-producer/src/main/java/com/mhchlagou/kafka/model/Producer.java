package com.mhchlagou.kafka.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Producer {

		private Long idProducer;
		private String name;
		private String dept;
		private Long salary;

		public Producer() {
			super();
		}

		public Producer(Long idProducer, String name, String dept, Long salary) {
			super();
			this.idProducer = idProducer;
			this.name = name;
			this.dept = dept;
			this.salary = salary;
		}

		@Id
		@GeneratedValue
		public Long getIdProducer() {
			return idProducer;
		}


		public void setIdProducer(Long idProducer) {
			this.idProducer = idProducer;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getDept() {
			return dept;
		}


		public void setDept(String dept) {
			this.dept = dept;
		}


		public Long getSalary() {
			return salary;
		}


		public void setSalary(Long salary) {
			this.salary = salary;
		}
		
		
}
