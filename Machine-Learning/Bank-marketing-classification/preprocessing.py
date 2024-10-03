import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.preprocessing import OneHotEncoder
from imblearn.over_sampling import SMOTE
import os

# Load the dataset
file_path = r"C:\Users\georg\Desktop\bank-additional-full.csv"
data = pd.read_csv(file_path, sep=';')

# Print all columns and their data types
print("Columns and their data types:")
print(data.dtypes)

# Drop the 'duration' column
data = data.drop(columns=['duration'])

# Print the number of duplicate rows
num_duplicates = data.duplicated().sum()
print(f"Number of duplicate rows: {num_duplicates}")

# Remove duplicate rows
data = data.drop_duplicates()

# Print columns with 'unknown' values and the number of 'unknown' values for each column
unknowns_info = {column: data[column].value_counts().get('unknown', 0) for column in data.columns if 'unknown' in data[column].unique()}
print("Columns with 'unknown' values and their counts:")
for column, count in unknowns_info.items():
    print(f"{column}: {count}")

# Specific handling for 'unknown' values in categorical columns
data.loc[(data['age'] > 60) & (data['job'] == 'unknown'), 'job'] = 'retired'
data.loc[(data['education'] == 'unknown') & (data['job'] == 'management'), 'education'] = 'university.degree'
data.loc[(data['education'] == 'unknown') & (data['job'] == 'services'), 'education'] = 'high.school'
data.loc[(data['education'] == 'unknown') & (data['job'] == 'housemaid'), 'education'] = 'basic.4y'
data.loc[(data['job'] == 'unknown') & (data['education'] == 'basic.4y'), 'job'] = 'blue-collar'
data.loc[(data['job'] == 'unknown') & (data['education'] == 'basic.6y'), 'job'] = 'blue-collar'
data.loc[(data['job'] == 'unknown') & (data['education'] == 'basic.9y'), 'job'] = 'blue-collar'
data.loc[(data['job'] == 'unknown') & (data['education'] == 'professional.course'), 'job'] = 'technician'

# Handle remaining 'unknown' values by imputing with the mode for categorical columns
for column in data.select_dtypes(include=['object']).columns:
    if 'unknown' in data[column].unique():
        data[column] = data[column].replace('unknown', pd.NA)
        data[column] = data[column].fillna(data[column].mode()[0])

# Binning the 'age' feature
age_bins = [0, 30, 40, 50, 60, np.inf]
age_labels = ['0-30', '31-40', '41-50', '51-60', '60+']
data['age_binned'] = pd.cut(data['age'], bins=age_bins, labels=age_labels)

# Encode categorical variables
categorical_columns = ['job', 'marital', 'education', 'default', 'housing', 'loan', 'contact', 'month', 'day_of_week', 'poutcome', 'age_binned']
encoder = OneHotEncoder(drop='first', sparse_output=False)
encoded_columns = pd.DataFrame(encoder.fit_transform(data[categorical_columns]), columns=encoder.get_feature_names_out(categorical_columns))

# Concatenate encoded columns with original data
data = pd.concat([data.drop(columns=categorical_columns), encoded_columns], axis=1)

# Separate features and target variable
X = data.drop(columns=['y'])
y = data['y'].map({'no': 0, 'yes': 1})  # Convert target variable to binary

# Ensure no NaN values in X and y
X = X.fillna(X.mean())
y = y.fillna(y.mode()[0])

# Handle class imbalance using SMOTE
smote = SMOTE(random_state=42)
X_resampled, y_resampled = smote.fit_resample(X, y)

# Split the data into training and testing sets (70-30 split)
X_train, X_test, y_train, y_test = train_test_split(X_resampled, y_resampled, test_size=0.3, random_state=42)

# Feature scaling after splitting the data
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)

desktop_path = os.path.join(os.path.join(os.environ['USERPROFILE']), 'Desktop')

# Save preprocessed data to CSV files 
pd.DataFrame(X_train_scaled, columns=X.columns).to_csv(os.path.join(desktop_path, 'X_train_scaled.csv'), index=False)
pd.DataFrame(X_test_scaled, columns=X.columns).to_csv(os.path.join(desktop_path, 'X_test_scaled.csv'), index=False)
pd.DataFrame(y_train).to_csv(os.path.join(desktop_path, 'y_train.csv'), index=False)
pd.DataFrame(y_test).to_csv(os.path.join(desktop_path, 'y_test.csv'), index=False)

print("Preprocessing complete. Preprocessed data saved to the desktop.")
