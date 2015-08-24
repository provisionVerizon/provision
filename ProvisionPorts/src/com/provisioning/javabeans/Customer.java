package com.provisioning.javabeans;

public class Customer {

		private String CUSTOMER_ID;
		private String CUSTOMER_NAME;
		private String CUSTOMER_TYPE;
		private String STREETNAME;
		private int ZIPCODE;
		private String CITY;
		private String STATE;
		private String COUNTRY;
		/**
		 * @return the cUSTOMER_ID
		 */
		public String getCUSTOMER_ID() {
			return CUSTOMER_ID;
		}
		/**
		 * @param cUSTOMER_ID the cUSTOMER_ID to set
		 */
		public void setCUSTOMER_ID(String cUSTOMER_ID) {
			CUSTOMER_ID = cUSTOMER_ID;
		}
		/**
		 * @return the cUSTOMER_NAME
		 */
		public String getCUSTOMER_NAME() {
			return CUSTOMER_NAME;
		}
		/**
		 * @param cUSTOMER_NAME the cUSTOMER_NAME to set
		 */
		public void setCUSTOMER_NAME(String cUSTOMER_NAME) {
			CUSTOMER_NAME = cUSTOMER_NAME;
		}
		/**
		 * @return the cUSTOMER_TYPE
		 */
		public String getCUSTOMER_TYPE() {
			return CUSTOMER_TYPE;
		}
		/**
		 * @param cUSTOMER_TYPE the cUSTOMER_TYPE to set
		 */
		public void setCUSTOMER_TYPE(String cUSTOMER_TYPE) {
			CUSTOMER_TYPE = cUSTOMER_TYPE;
		}
		/**
		 * @return the sTREETNAME
		 */
		public String getSTREETNAME() {
			return STREETNAME;
		}
		/**
		 * @param sTREETNAME the sTREETNAME to set
		 */
		public void setSTREETNAME(String sTREETNAME) {
			STREETNAME = sTREETNAME;
		}
		/**
		 * @return the zIPCODE
		 */
		public int getZIPCODE() {
			return ZIPCODE;
		}
		/**
		 * @param zIPCODE the zIPCODE to set
		 */
		public void setZIPCODE(int zIPCODE) {
			ZIPCODE = zIPCODE;
		}
		/**
		 * @return the cITY
		 */
		public String getCITY() {
			return CITY;
		}
		/**
		 * @param cITY the cITY to set
		 */
		public void setCITY(String cITY) {
			CITY = cITY;
		}
		/**
		 * @return the sTATE
		 */
		public String getSTATE() {
			return STATE;
		}
		/**
		 * @param sTATE the sTATE to set
		 */
		public void setSTATE(String sTATE) {
			STATE = sTATE;
		}
		/**
		 * @return the cOUNTRY
		 */
		public String getCOUNTRY() {
			return COUNTRY;
		}
		/**
		 * @param cOUNTRY the cOUNTRY to set
		 */
		public void setCOUNTRY(String cOUNTRY) {
			COUNTRY = cOUNTRY;
		}
		
		public String toString()
		{
			return "got the customer object modify toString to get details ";
		}
}
